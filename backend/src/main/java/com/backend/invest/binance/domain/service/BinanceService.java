package com.backend.invest.binance.domain.service;

import java.util.List;
import java.util.Optional;

import com.backend.invest.binance.domain.CryptoConvertResult;
import com.backend.invest.binance.domain.CryptoPrice;
import com.backend.invest.binance.domain.port.in.ConvertCryptoUseCase;
import com.backend.invest.binance.domain.port.in.GetCryptoPricesUseCase;
import com.backend.invest.binance.domain.port.in.command.ConvertCryptoCommand;
import com.backend.invest.binance.domain.port.out.BinanceConvertPort;
import com.backend.invest.binance.domain.port.out.BinanceMarketDataPort;

public class BinanceService implements GetCryptoPricesUseCase, ConvertCryptoUseCase {
  private final BinanceMarketDataPort marketDataPort;
  private final BinanceConvertPort convertPort;

  public BinanceService(BinanceMarketDataPort marketDataPort, BinanceConvertPort convertPort) {
    this.marketDataPort = marketDataPort;
    this.convertPort = convertPort;
  }

  @Override
  public List<CryptoPrice> listPrices() {
    return marketDataPort.fetchAllPrices();
  }

  @Override
  public Optional<CryptoPrice> getPriceBySymbol(String symbol) {
    return marketDataPort.fetchPriceBySymbol(symbol);
  }

  @Override
  public CryptoConvertResult convert(ConvertCryptoCommand command) {
    if (command.apiKey() == null || command.apiKey().isBlank() || command.apiSecret() == null
        || command.apiSecret().isBlank()) {
      throw new IllegalArgumentException("Binance API key and secret are required for conversion");
    }
    if (command.fromAsset() == null || command.fromAsset().isBlank() || command.toAsset() == null
        || command.toAsset().isBlank()) {
      throw new IllegalArgumentException("Source and destination assets are required");
    }
    if (command.fromAmount() == null || command.fromAmount() <= 0) {
      throw new IllegalArgumentException("Conversion amount must be greater than zero");
    }

    ConvertCryptoCommand normalized = new ConvertCryptoCommand(command.apiKey().trim(),
        command.apiSecret().trim(), command.fromAsset().trim().toUpperCase(),
        command.toAsset().trim().toUpperCase(), command.fromAmount());

    return convertPort.convert(normalized);
  }
}
