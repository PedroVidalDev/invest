package com.backend.invest.userAlert.adapter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backend.invest.user.domain.port.in.UserQueryUseCase;
import com.backend.invest.userAlert.domain.port.out.UserAlertRepositoryPort;
import com.backend.invest.userAlert.domain.service.UserAlertService;

@Configuration
public class UserAlertBeanConfig {
  @Bean
  public UserAlertService userAlertService(UserAlertRepositoryPort repositoryPort,
      UserQueryUseCase userQuery) {
    return new UserAlertService(repositoryPort, userQuery);
  }
}
