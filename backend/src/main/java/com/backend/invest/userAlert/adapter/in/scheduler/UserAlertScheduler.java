package com.backend.invest.userAlert.adapter.in.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.backend.invest.userAlert.domain.port.in.CheckUserAlertsUseCase;

@Component
public class UserAlertScheduler {
  private final CheckUserAlertsUseCase checkUserAlerts;

  public UserAlertScheduler(CheckUserAlertsUseCase checkUserAlerts) {
    this.checkUserAlerts = checkUserAlerts;
  }

  @Scheduled(fixedDelayString = "${alert.check.delay-ms:60000}")
  public void checkAlerts() {
    checkUserAlerts.checkActiveAlerts();
  }
}
