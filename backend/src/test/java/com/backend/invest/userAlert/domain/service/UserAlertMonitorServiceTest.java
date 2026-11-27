package com.backend.invest.userAlert.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import com.backend.invest.binance.domain.CryptoPrice;
import com.backend.invest.binance.domain.port.in.GetCryptoPricesUseCase;
import com.backend.invest.instrument.domain.Instrument;
import com.backend.invest.instrument.domain.InstrumentType;
import com.backend.invest.instrument.domain.port.in.InstrumentQueryUseCase;
import com.backend.invest.userAlert.domain.AlertCondition;
import com.backend.invest.userAlert.domain.UserAlert;
import com.backend.invest.userAlert.domain.port.out.AlertActionPort;
import com.backend.invest.userAlert.domain.port.out.UserAlertRepositoryPort;

class UserAlertMonitorServiceTest {
  private UserAlertRepositoryPort alertRepository;
  private InstrumentQueryUseCase instrumentQuery;
  private GetCryptoPricesUseCase cryptoPrices;
  private AlertActionPort alertAction;
  private UserAlertMonitorService monitorService;

  @BeforeEach
  void setUp() {
    alertRepository = mock(UserAlertRepositoryPort.class);
    instrumentQuery = mock(InstrumentQueryUseCase.class);
    cryptoPrices = mock(GetCryptoPricesUseCase.class);
    alertAction = mock(AlertActionPort.class);
    monitorService =
        new UserAlertMonitorService(alertRepository, instrumentQuery, cryptoPrices, alertAction);
  }

  @Test
  void triggersActionAndDeactivatesAlertWhenPriceMatchesBelowCondition() {
    LocalDateTime now = LocalDateTime.now();
    UserAlert alert = new UserAlert("alert-1", "user-1", "inst-btc", null, 60000.0,
        AlertCondition.BELOW, 100.0, "BTC", true, now, now);
    Instrument btc = new Instrument("inst-btc", InstrumentType.CRYPTO, "BTCUSDT", "Bitcoin", null,
        null, now, now);

    when(alertRepository.findByActiveTrue()).thenReturn(List.of(alert));
    when(instrumentQuery.findById("inst-btc")).thenReturn(Optional.of(btc));
    when(cryptoPrices.getPriceBySymbol("BTCUSDT"))
        .thenReturn(Optional.of(new CryptoPrice("BTCUSDT", 59500.0)));

    monitorService.checkActiveAlerts();

    verify(alertAction).execute(alert, btc, 59500.0);

    ArgumentCaptor<UserAlert> savedCaptor = ArgumentCaptor.forClass(UserAlert.class);
    verify(alertRepository).save(savedCaptor.capture());
    assertFalse(savedCaptor.getValue().getActive());
    assertEquals("alert-1", savedCaptor.getValue().getId());
    assertEquals(AlertCondition.BELOW, savedCaptor.getValue().getCondition());
    assertEquals(100.0, savedCaptor.getValue().getAutoConvertFromValue());
    assertEquals("BTC", savedCaptor.getValue().getAutoConvertTo());
  }

  @Test
  void triggersActionAndDeactivatesAlertWhenPriceMatchesAboveCondition() {
    LocalDateTime now = LocalDateTime.now();
    UserAlert alert = new UserAlert("alert-2", "user-1", "inst-btc", null, 60000.0,
        AlertCondition.ABOVE, 100.0, "USDT", true, now, now);
    Instrument btc = new Instrument("inst-btc", InstrumentType.CRYPTO, "BTCUSDT", "Bitcoin", null,
        null, now, now);

    when(alertRepository.findByActiveTrue()).thenReturn(List.of(alert));
    when(instrumentQuery.findById("inst-btc")).thenReturn(Optional.of(btc));
    when(cryptoPrices.getPriceBySymbol("BTCUSDT"))
        .thenReturn(Optional.of(new CryptoPrice("BTCUSDT", 61000.0)));

    monitorService.checkActiveAlerts();

    verify(alertAction).execute(alert, btc, 61000.0);

    ArgumentCaptor<UserAlert> savedCaptor = ArgumentCaptor.forClass(UserAlert.class);
    verify(alertRepository).save(savedCaptor.capture());
    assertFalse(savedCaptor.getValue().getActive());
    assertEquals(AlertCondition.ABOVE, savedCaptor.getValue().getCondition());
  }

  @Test
  void doesNotTriggerWhenPriceDoesNotMatchCondition() {
    LocalDateTime now = LocalDateTime.now();
    UserAlert belowAlert = new UserAlert("alert-1", "user-1", "inst-btc", null, 60000.0,
        AlertCondition.BELOW, null, null, true, now, now);
    UserAlert aboveAlert = new UserAlert("alert-2", "user-1", "inst-btc", null, 70000.0,
        AlertCondition.ABOVE, null, null, true, now, now);
    UserAlert nullConditionAlert = new UserAlert("alert-3", "user-1", "inst-btc", null, 65000.0,
        null, null, null, true, now, now);
    Instrument btc = new Instrument("inst-btc", InstrumentType.CRYPTO, "BTCUSDT", "Bitcoin", null,
        null, now, now);

    when(alertRepository.findByActiveTrue())
        .thenReturn(List.of(belowAlert, aboveAlert, nullConditionAlert));
    when(instrumentQuery.findById("inst-btc")).thenReturn(Optional.of(btc));
    when(cryptoPrices.getPriceBySymbol("BTCUSDT"))
        .thenReturn(Optional.of(new CryptoPrice("BTCUSDT", 65000.0)));

    monitorService.checkActiveAlerts();

    verify(alertAction, never()).execute(any(), any(), any());
    verify(alertRepository, never()).save(any());
  }

  @Test
  void skipsNonCryptoInstruments() {
    LocalDateTime now = LocalDateTime.now();
    UserAlert alert = new UserAlert("alert-1", "user-1", "inst-stock", null, 30.0,
        AlertCondition.BELOW, null, null, true, now, now);
    Instrument stock = new Instrument("inst-stock", InstrumentType.STOCK, "PETR4", "Petrobras",
        null, null, now, now);

    when(alertRepository.findByActiveTrue()).thenReturn(List.of(alert));
    when(instrumentQuery.findById("inst-stock")).thenReturn(Optional.of(stock));

    monitorService.checkActiveAlerts();

    verify(cryptoPrices, never()).getPriceBySymbol(any());
    verify(alertAction, never()).execute(any(), any(), any());
  }

  @Test
  void continuesProcessingRemainingAlertsAndReusesPriceLookupWhenOneAlertFails() {
    LocalDateTime now = LocalDateTime.now();
    UserAlert failingAlert = new UserAlert("alert-1", "user-1", "inst-btc", null, 60000.0,
        AlertCondition.BELOW, null, null, true, now, now);
    UserAlert okAlert = new UserAlert("alert-2", "user-2", "inst-btc", null, 60000.0,
        AlertCondition.BELOW, null, null, true, now, now);
    Instrument btc = new Instrument("inst-btc", InstrumentType.CRYPTO, "BTCUSDT", "Bitcoin", null,
        null, now, now);

    when(alertRepository.findByActiveTrue()).thenReturn(List.of(failingAlert, okAlert));
    when(instrumentQuery.findById("inst-btc")).thenReturn(Optional.of(btc));
    when(cryptoPrices.getPriceBySymbol("BTCUSDT"))
        .thenReturn(Optional.of(new CryptoPrice("BTCUSDT", 59000.0)));
    doThrow(new RuntimeException("action error")).when(alertAction).execute(failingAlert, btc,
        59000.0);

    monitorService.checkActiveAlerts();

    verify(cryptoPrices, times(1)).getPriceBySymbol("BTCUSDT");
    verify(alertAction).execute(okAlert, btc, 59000.0);
    verify(alertRepository, times(1)).save(any());
  }
}
