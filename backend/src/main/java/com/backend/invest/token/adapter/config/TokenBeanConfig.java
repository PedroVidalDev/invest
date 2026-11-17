package com.backend.invest.token.adapter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backend.invest.token.domain.port.out.TokenRepositoryPort;
import com.backend.invest.token.domain.service.TokenService;

@Configuration
public class TokenBeanConfig {
    @Bean
    public TokenService tokenService(TokenRepositoryPort repositoryPort) {
        return new TokenService(repositoryPort);
    }
}
