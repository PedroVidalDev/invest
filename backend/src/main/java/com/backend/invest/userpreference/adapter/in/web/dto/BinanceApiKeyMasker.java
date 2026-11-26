package com.backend.invest.userpreference.adapter.in.web.dto;

final class BinanceApiKeyMasker {
    private static final String MASK_PREFIX = "xxxx";
    private static final int VISIBLE_SUFFIX_LENGTH = 4;

    private BinanceApiKeyMasker() {}

    static String mask(String apiKey) {
        if (apiKey == null || apiKey.isBlank()) {
            return null;
        }
        if (apiKey.length() <= VISIBLE_SUFFIX_LENGTH) {
            return MASK_PREFIX;
        }
        return MASK_PREFIX + apiKey.substring(apiKey.length() - VISIBLE_SUFFIX_LENGTH);
    }
}