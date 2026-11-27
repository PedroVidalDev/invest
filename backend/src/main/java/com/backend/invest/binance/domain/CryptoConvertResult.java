package com.backend.invest.binance.domain;

public record CryptoConvertResult(String quoteId, String orderId, String orderStatus,
    String fromAsset, String toAsset, Double fromAmount, Double toAmount) {
}
