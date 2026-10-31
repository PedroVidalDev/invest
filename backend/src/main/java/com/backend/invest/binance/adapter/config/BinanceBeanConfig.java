package com.backend.invest.binance.adapter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backend.invest.binance.domain.port.out.BinanceMarketDataPort;
import com.backend.invest.binance.domain.service.BinanceService;

@Configuration
public class BinanceBeanConfig {
  @Bean
  public BinanceService binanceService(BinanceMarketDataPort marketDataPort) {
    return new BinanceService(marketDataPort);
  }
}
