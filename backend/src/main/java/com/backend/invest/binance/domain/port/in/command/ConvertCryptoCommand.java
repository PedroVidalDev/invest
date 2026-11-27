package com.backend.invest.binance.domain.port.in.command;

public record ConvertCryptoCommand(String apiKey, String apiSecret, String fromAsset,
    String toAsset, Double fromAmount) {
}
