package com.backend.invest.binance.domain;

import java.io.Serializable;

public class CryptoPrice implements Serializable {
  private final String symbol;
  private final Double price;

  private static final long serialVersionUID = 1L;

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
