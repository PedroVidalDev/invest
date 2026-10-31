package com.backend.invest.binance.adapter.in.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.invest.binance.adapter.out.rest.dto.BinancePriceResponse;
import com.backend.invest.binance.domain.CryptoPrice;
import com.backend.invest.binance.domain.port.in.GetCryptoPricesUseCase;

@RestController
@RequestMapping("/binance")
public class BinanceController {
  private final GetCryptoPricesUseCase getCryptoPricesUseCase;

  public BinanceController(GetCryptoPricesUseCase getCryptoPricesUseCase) {
    this.getCryptoPricesUseCase = getCryptoPricesUseCase;
  }

  @GetMapping("/prices")
  public ResponseEntity<List<BinancePriceResponse>> listPrices() {
    List<BinancePriceResponse> prices =
        getCryptoPricesUseCase.listPrices().stream().map(BinancePriceResponse::from).toList();
    return ResponseEntity.ok(prices);
  }

  @GetMapping("/prices/{symbol}")
  public ResponseEntity<BinancePriceResponse> getPriceBySymbol(@PathVariable String symbol) {
    CryptoPrice price = getCryptoPricesUseCase.getPriceBySymbol(symbol)
        .orElseThrow(() -> new RuntimeException("Price not found for symbol: " + symbol));
    BinancePriceResponse priceResponse = BinancePriceResponse.from(price);
    return ResponseEntity.ok(priceResponse);
  }
}
