package com.backend.invest.userpreference.domain.exception;

public class InvalidBinanceApiKeyLengthException extends RuntimeException {
    public InvalidBinanceApiKeyLengthException(int maxLength) {
        super("Binance API key exceeds maximum allowed length of " + maxLength + " characters");
    }
}