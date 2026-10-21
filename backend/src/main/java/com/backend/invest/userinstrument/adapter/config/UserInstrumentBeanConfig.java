package com.backend.invest.userinstrument.adapter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backend.invest.userinstrument.domain.port.out.UserInstrumentRepositoryPort;
import com.backend.invest.userinstrument.domain.service.UserInstrumentService;

@Configuration
public class UserInstrumentBeanConfig {
    @Bean
    public UserInstrumentService userInstrumentService(UserInstrumentRepositoryPort repositoryPort) {
        return new UserInstrumentService(repositoryPort);
    }
}