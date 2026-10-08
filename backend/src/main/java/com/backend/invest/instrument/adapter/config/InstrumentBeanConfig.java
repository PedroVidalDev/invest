package com.backend.invest.instrument.adapter.config;

import com.backend.invest.instrument.domain.port.out.InstrumentRepositoryPort;
import com.backend.invest.instrument.domain.service.InstrumentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InstrumentBeanConfig {
    @Bean
    public InstrumentService instrumentService(InstrumentRepositoryPort repositoryPort) {
        return new InstrumentService(repositoryPort);
    }
}
