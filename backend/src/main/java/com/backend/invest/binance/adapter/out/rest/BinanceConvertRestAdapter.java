package com.backend.invest.binance.adapter.out.rest;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.HexFormat;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.backend.invest.binance.adapter.out.rest.dto.BinanceConvertAcceptResponse;
import com.backend.invest.binance.adapter.out.rest.dto.BinanceConvertQuoteResponse;
import com.backend.invest.binance.domain.CryptoConvertResult;
import com.backend.invest.binance.domain.port.in.command.ConvertCryptoCommand;
import com.backend.invest.binance.domain.port.out.BinanceConvertPort;

@Component
public class BinanceConvertRestAdapter implements BinanceConvertPort {
  private static final String HMAC_SHA256 = "HmacSHA256";
  private static final String API_KEY_HEADER = "X-MBX-APIKEY";

  private final RestClient restClient;
  private final String baseUrl;

  public BinanceConvertRestAdapter(RestClient.Builder builder,
      @Value("${BINANCE_API}") String baseUrl) {
    this.baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
    this.restClient = builder.build();
  }

  @Override
  public CryptoConvertResult convert(ConvertCryptoCommand command) {
    String amountPlain = BigDecimal.valueOf(command.fromAmount()).toPlainString();

    String quoteQuery =
        "fromAsset=" + encode(command.fromAsset()) + "&toAsset=" + encode(command.toAsset())
            + "&fromAmount=" + encode(amountPlain) + "&timestamp=" + System.currentTimeMillis();
    String quoteSignature = sign(quoteQuery, command.apiSecret());

    BinanceConvertQuoteResponse quote = restClient.post()
        .uri(URI.create(
            baseUrl + "/sapi/v1/convert/getQuote?" + quoteQuery + "&signature=" + quoteSignature))
        .header(API_KEY_HEADER, command.apiKey()).retrieve()
        .body(BinanceConvertQuoteResponse.class);

    if (quote == null || quote.quoteId() == null || quote.quoteId().isBlank()) {
      throw new IllegalStateException("Binance convert getQuote did not return a quoteId");
    }

    String acceptQuery =
        "quoteId=" + encode(quote.quoteId()) + "&timestamp=" + System.currentTimeMillis();
    String acceptSignature = sign(acceptQuery, command.apiSecret());

    BinanceConvertAcceptResponse accept = restClient.post()
        .uri(URI.create(baseUrl + "/sapi/v1/convert/acceptQuote?" + acceptQuery + "&signature="
            + acceptSignature))
        .header(API_KEY_HEADER, command.apiKey()).retrieve()
        .body(BinanceConvertAcceptResponse.class);

    if (accept == null || accept.orderId() == null) {
      throw new IllegalStateException("Binance convert acceptQuote did not return an orderId");
    }

    return new CryptoConvertResult(quote.quoteId(), accept.orderId(), accept.orderStatus(),
        command.fromAsset(), command.toAsset(),
        quote.fromAmount() != null ? quote.fromAmount() : command.fromAmount(), quote.toAmount());
  }

  static String sign(String payload, String secret) {
    try {
      Mac mac = Mac.getInstance(HMAC_SHA256);
      mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), HMAC_SHA256));
      byte[] hash = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
      return HexFormat.of().formatHex(hash);
    } catch (GeneralSecurityException e) {
      throw new IllegalStateException("Failed to sign Binance request", e);
    }
  }

  private static String encode(String value) {
    return URLEncoder.encode(value, StandardCharsets.UTF_8);
  }
}
