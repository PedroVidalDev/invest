package com.backend.invest.binance.domain.port.in;

import java.util.List;
import java.util.Optional;

import com.backend.invest.binance.domain.CryptoPrice;

public interface GetCryptoPricesUseCase {
  List<CryptoPrice> listPrices();

  Optional<CryptoPrice> getPriceBySymbol(String symbol);
}
