package com.backend.invest.binance.domain.service;

import java.util.List;
import java.util.Optional;

import com.backend.invest.binance.domain.CryptoPrice;
import com.backend.invest.binance.domain.port.in.GetCryptoPricesUseCase;
import com.backend.invest.binance.domain.port.out.BinanceMarketDataPort;

public class BinanceService implements GetCryptoPricesUseCase {
  private final BinanceMarketDataPort marketDataPort;

  public BinanceService(BinanceMarketDataPort marketDataPort) {
    this.marketDataPort = marketDataPort;
  }

  @Override
  public List<CryptoPrice> listPrices() {
    return marketDataPort.fetchAllPrices();
  }

  @Override
  public Optional<CryptoPrice> getPriceBySymbol(String symbol) {
    return marketDataPort.fetchPriceBySymbol(symbol);
  }
}
