package com.backend.invest.userAlert.domain.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.backend.invest.binance.domain.CryptoPrice;
import com.backend.invest.binance.domain.port.in.GetCryptoPricesUseCase;
import com.backend.invest.instrument.domain.Instrument;
import com.backend.invest.instrument.domain.InstrumentType;
import com.backend.invest.instrument.domain.port.in.InstrumentQueryUseCase;
import com.backend.invest.userAlert.domain.UserAlert;
import com.backend.invest.userAlert.domain.port.in.CheckUserAlertsUseCase;
import com.backend.invest.userAlert.domain.port.out.AlertActionPort;
import com.backend.invest.userAlert.domain.port.out.UserAlertRepositoryPort;

public class UserAlertMonitorService implements CheckUserAlertsUseCase {
  private static final Logger log = LoggerFactory.getLogger(UserAlertMonitorService.class);

  private final UserAlertRepositoryPort alertRepository;
  private final InstrumentQueryUseCase instrumentQuery;
  private final GetCryptoPricesUseCase cryptoPrices;
  private final AlertActionPort alertAction;

  public UserAlertMonitorService(UserAlertRepositoryPort alertRepository,
      InstrumentQueryUseCase instrumentQuery, GetCryptoPricesUseCase cryptoPrices,
      AlertActionPort alertAction) {
    this.alertRepository = alertRepository;
    this.instrumentQuery = instrumentQuery;
    this.cryptoPrices = cryptoPrices;
    this.alertAction = alertAction;
  }

  @Override
  public void checkActiveAlerts() {
    List<UserAlert> activeAlerts = alertRepository.findByActiveTrue();
    Map<String, Optional<Double>> priceCache = new HashMap<>();

    for (UserAlert alert : activeAlerts) {
      try {
        processAlert(alert, priceCache);
      } catch (Exception e) {
        log.error("Failed to check user alert {}", alert.getId(), e);
      }
    }
  }

  private void processAlert(UserAlert alert, Map<String, Optional<Double>> priceCache) {
    Optional<Instrument> instrumentOpt = instrumentQuery.findById(alert.getInstrumentId());
    if (instrumentOpt.isEmpty()) {
      return;
    }

    Instrument instrument = instrumentOpt.get();
    if (instrument.getType() != InstrumentType.CRYPTO || instrument.getSymbol() == null
        || instrument.getSymbol().isBlank()) {
      return;
    }

    Optional<Double> currentPriceOpt = priceCache.computeIfAbsent(instrument.getSymbol(),
        symbol -> cryptoPrices.getPriceBySymbol(symbol).map(CryptoPrice::getPrice));
    if (currentPriceOpt.isEmpty() || currentPriceOpt.get() == null) {
      return;
    }

    Double currentPrice = currentPriceOpt.get();
    if (matchesCondition(alert, currentPrice)) {
      alertAction.execute(alert, instrument, currentPrice);
      deactivateAlert(alert);
    }
  }

  private boolean matchesCondition(UserAlert alert, Double currentPrice) {
    if (alert.getValue() != null && alert.getCondition() != null) {
      return switch (alert.getCondition()) {
        case ABOVE -> currentPrice >= alert.getValue();
        case BELOW -> currentPrice <= alert.getValue();
      };
    }

    // TODO: evaluate alert.getPercent() once a baseline/reference price is defined.
    return false;
  }

  private void deactivateAlert(UserAlert alert) {
    UserAlert deactivated = new UserAlert(alert.getId(), alert.getUserId(), alert.getInstrumentId(),
        alert.getPercent(), alert.getValue(), alert.getCondition(), alert.getAutoConvertFromValue(),
        alert.getAutoConvertTo(), false, alert.getCreatedAt(), LocalDateTime.now());
    alertRepository.save(deactivated);
  }
}
