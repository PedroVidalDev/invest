package com.backend.invest.userAlert.adapter.out;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.backend.invest.instrument.domain.Instrument;
import com.backend.invest.userAlert.domain.UserAlert;
import com.backend.invest.userAlert.domain.port.out.AlertActionPort;

@Component
public class LoggingAlertActionAdapter implements AlertActionPort {
  private static final Logger log = LoggerFactory.getLogger(LoggingAlertActionAdapter.class);

  @Override
  public void execute(UserAlert alert, Instrument instrument, Double currentPrice) {
    log.info("Alert {} triggered for {} at price {}", alert.getId(), instrument.getSymbol(),
        currentPrice);
  }
}
