package com.backend.invest.binance.domain;

public class CryptoPrice {
  private final String symbol;
  private final Double price;

  public CryptoPrice(String symbol, Double price) {
    this.symbol = symbol;
    this.price = price;
  }

  public String getSymbol() {
    return symbol;
  }

  public Double getPrice() {
    return price;
  }
}
