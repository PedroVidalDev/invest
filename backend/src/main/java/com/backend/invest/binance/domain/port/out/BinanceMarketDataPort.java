package com.backend.invest.binance.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.backend.invest.binance.domain.CryptoPrice;

public interface BinanceMarketDataPort {
  List<CryptoPrice> fetchAllPrices();

  Optional<CryptoPrice> fetchPriceBySymbol(String symbol);
}
