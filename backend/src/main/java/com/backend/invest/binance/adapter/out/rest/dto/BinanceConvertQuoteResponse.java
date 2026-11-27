package com.backend.invest.binance.adapter.out.rest.dto;

public record BinanceConvertQuoteResponse(String quoteId, String ratio, String inverseRatio,
    Long validTimestamp, Double toAmount, Double fromAmount) {
}
