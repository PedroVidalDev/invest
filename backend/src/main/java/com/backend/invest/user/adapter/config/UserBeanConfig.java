package com.backend.invest.user.adapter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backend.invest.user.domain.port.out.UserRepositoryPort;
import com.backend.invest.user.domain.service.UserService;

@Configuration 
public class UserBeanConfig {
    @Bean
    public UserService userService(UserRepositoryPort repositoryPort) {
        return new UserService(repositoryPort);
    }
}
