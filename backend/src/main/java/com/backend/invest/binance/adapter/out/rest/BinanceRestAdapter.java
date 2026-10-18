package com.backend.invest.binance.adapter.out.rest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.backend.invest.binance.adapter.out.rest.dto.BinancePriceResponse;
import com.backend.invest.binance.domain.CryptoPrice;
import com.backend.invest.binance.domain.port.out.BinanceMarketDataPort;

@Component
public class BinanceRestAdapter implements BinanceMarketDataPort {
  private final RestClient restClient;

  public BinanceRestAdapter(RestClient.Builder builder, @Value("${BINANCE_API}") String baseUrl) {
    this.restClient = builder.baseUrl(baseUrl).build();
  }

  @Override
  @Cacheable(cacheNames = "all-cryptos")
  public List<CryptoPrice> fetchAllPrices() {
    BinancePriceResponse[] body =
        restClient.get().uri("/api/v3/ticker/price").retrieve().body(BinancePriceResponse[].class);

    if (body == null) {
      return List.of();
    }

    return Arrays.stream(body).filter(p -> p.symbol().endsWith("USDT")).limit(20)
        .map(p -> new CryptoPrice(p.symbol(), p.price())).toList();
  }

  @Override
  @Cacheable(cacheNames = "crypto-prices", key = "#symbol")
  public Optional<CryptoPrice> fetchPriceBySymbol(String symbol) {
    BinancePriceResponse body =
        restClient.get().uri(b -> b.path("/api/v3/ticker").queryParam("symbol", symbol).build())
            .retrieve().body(BinancePriceResponse.class);

    if (body == null) {
      return Optional.empty();
    }

    return Optional.of(new CryptoPrice(body.symbol(), body.price()));
  }
}
