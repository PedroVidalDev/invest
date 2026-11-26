package com.backend.invest.userAlert.adapter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backend.invest.binance.domain.port.in.GetCryptoPricesUseCase;
import com.backend.invest.instrument.domain.port.in.InstrumentQueryUseCase;
import com.backend.invest.user.domain.port.in.UserQueryUseCase;
import com.backend.invest.userAlert.domain.port.in.CheckUserAlertsUseCase;
import com.backend.invest.userAlert.domain.port.out.AlertActionPort;
import com.backend.invest.userAlert.domain.port.out.UserAlertRepositoryPort;
import com.backend.invest.userAlert.domain.service.UserAlertMonitorService;
import com.backend.invest.userAlert.domain.service.UserAlertService;

@Configuration
public class UserAlertBeanConfig {
  @Bean
  public UserAlertService userAlertService(UserAlertRepositoryPort repositoryPort,
      UserQueryUseCase userQuery) {
    return new UserAlertService(repositoryPort, userQuery);
  }

  @Bean
  public CheckUserAlertsUseCase checkUserAlertsUseCase(UserAlertRepositoryPort repositoryPort,
      InstrumentQueryUseCase instrumentQuery, GetCryptoPricesUseCase cryptoPrices,
      AlertActionPort alertAction) {
    return new UserAlertMonitorService(repositoryPort, instrumentQuery, cryptoPrices, alertAction);
  }
}
