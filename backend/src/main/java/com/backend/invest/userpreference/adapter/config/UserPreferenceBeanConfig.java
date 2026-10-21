package com.backend.invest.userpreference.adapter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backend.invest.userpreference.domain.port.out.UserPreferenceRepositoryPort;
import com.backend.invest.userpreference.domain.service.UserPreferenceService;

@Configuration 
public class UserPreferenceBeanConfig {
    @Bean 
    public UserPreferenceService userPreferenceService(UserPreferenceRepositoryPort repositoryPort) {
        return new UserPreferenceService(repositoryPort);
    }
}
