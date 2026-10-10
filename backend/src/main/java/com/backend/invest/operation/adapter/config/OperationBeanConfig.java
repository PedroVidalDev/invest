package com.backend.invest.operation.adapter.config;

import com.backend.invest.operation.domain.port.out.OperationRepositoryPort;
import com.backend.invest.operation.domain.service.OperationService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OperationBeanConfig {

  @Bean
  public OperationService operationService(OperationRepositoryPort repositoryPort) {
    return new OperationService(repositoryPort);
  }
}
