package com.backend.invest.userAlert.domain.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.backend.invest.binance.domain.CryptoConvertResult;
import com.backend.invest.binance.domain.CryptoPrice;
import com.backend.invest.binance.domain.port.in.ConvertCryptoUseCase;
import com.backend.invest.binance.domain.port.in.GetCryptoPricesUseCase;
import com.backend.invest.binance.domain.port.in.command.ConvertCryptoCommand;
import com.backend.invest.instrument.domain.Instrument;
import com.backend.invest.instrument.domain.InstrumentType;
import com.backend.invest.instrument.domain.port.in.InstrumentQueryUseCase;
import com.backend.invest.userAlert.domain.UserAlert;
import com.backend.invest.userAlert.domain.port.in.CheckUserAlertsUseCase;
import com.backend.invest.userAlert.domain.port.out.AlertActionPort;
import com.backend.invest.userAlert.domain.port.out.UserAlertRepositoryPort;
import com.backend.invest.userpreference.domain.UserPreference;
import com.backend.invest.userpreference.domain.exception.UserPreferenceNotFoundException;
import com.backend.invest.userpreference.domain.port.in.UserPreferenceQueryUseCase;

public class UserAlertMonitorService implements CheckUserAlertsUseCase {
  private static final Logger log = LoggerFactory.getLogger(UserAlertMonitorService.class);

  private final UserAlertRepositoryPort alertRepository;
  private final InstrumentQueryUseCase instrumentQuery;
  private final GetCryptoPricesUseCase cryptoPrices;
  private final AlertActionPort alertAction;
  private final UserPreferenceQueryUseCase userPreferenceQuery;
  private final ConvertCryptoUseCase convertCrypto;

  public UserAlertMonitorService(UserAlertRepositoryPort alertRepository,
      InstrumentQueryUseCase instrumentQuery, GetCryptoPricesUseCase cryptoPrices,
      AlertActionPort alertAction, UserPreferenceQueryUseCase userPreferenceQuery,
      ConvertCryptoUseCase convertCrypto) {
    this.alertRepository = alertRepository;
    this.instrumentQuery = instrumentQuery;
    this.cryptoPrices = cryptoPrices;
    this.alertAction = alertAction;
    this.userPreferenceQuery = userPreferenceQuery;
    this.convertCrypto = convertCrypto;
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

    String tickerSymbol = toTickerSymbol(instrument.getSymbol());
    Optional<Double> currentPriceOpt = priceCache.computeIfAbsent(tickerSymbol,
        symbol -> cryptoPrices.getPriceBySymbol(symbol).map(CryptoPrice::getPrice));
    if (currentPriceOpt.isEmpty() || currentPriceOpt.get() == null) {
      return;
    }

    Double currentPrice = currentPriceOpt.get();
    if (matchesCondition(alert, currentPrice)) {
      alertAction.execute(alert, instrument, currentPrice);
      executeAutoConvertIfConfigured(alert, instrument);
      deactivateAlert(alert);
    }
  }

  private void executeAutoConvertIfConfigured(UserAlert alert, Instrument instrument) {
    if (alert.getAutoConvertFromValue() == null || alert.getAutoConvertFromValue() <= 0
        || alert.getAutoConvertTo() == null || alert.getAutoConvertTo().isBlank()) {
      return;
    }

    Optional<UserPreference> preferenceOpt = findUserPreference(alert.getUserId());
    if (preferenceOpt.isEmpty()) {
      return;
    }

    UserPreference preference = preferenceOpt.get();
    String apiKey = preference.getUserBinanceApiKey();
    String apiSecret = preference.getUserBinanceApiSecret();

    if (apiKey == null || apiKey.isBlank()) {
      return;
    }

    if (apiSecret == null || apiSecret.isBlank()) {
      log.warn("Skipping Binance auto-convert for alert {}: userBinanceApiSecret is not configured",
          alert.getId());
      return;
    }

    ConvertCryptoCommand command = new ConvertCryptoCommand(apiKey, apiSecret,
        instrument.getSymbol(), alert.getAutoConvertTo(), alert.getAutoConvertFromValue());
    CryptoConvertResult result = convertCrypto.convert(command);
    log.info("Executed Binance convert for alert {}: orderId={}, status={}, {} {} -> {} {}",
        alert.getId(), result.orderId(), result.orderStatus(), result.fromAmount(),
        result.fromAsset(), result.toAmount(), result.toAsset());
  }

  private Optional<UserPreference> findUserPreference(String userId) {
    try {
      return Optional.ofNullable(userPreferenceQuery.findByUserId(userId));
    } catch (UserPreferenceNotFoundException e) {
      return Optional.empty();
    }
  }

  private String toTickerSymbol(String assetSymbol) {
    String normalized = assetSymbol.trim().toUpperCase();
    return normalized.endsWith("USDT") ? normalized : normalized + "USDT";
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
