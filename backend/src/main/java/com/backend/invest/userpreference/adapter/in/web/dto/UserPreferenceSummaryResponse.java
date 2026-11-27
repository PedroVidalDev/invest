package com.backend.invest.userpreference.adapter.in.web.dto;

import com.backend.invest.userpreference.domain.DisplayCurrencyType;
import com.backend.invest.userpreference.domain.LanguageType;
import com.backend.invest.userpreference.domain.ThemeType;
import com.backend.invest.userpreference.domain.UserPreference;

public record UserPreferenceSummaryResponse(String id, String userId, String userBinanceApiKey,
    String userBinanceApiSecret, DisplayCurrencyType displayCurrency, LanguageType language,
    ThemeType theme) {

  public static UserPreferenceSummaryResponse from(UserPreference userPreference) {
    return new UserPreferenceSummaryResponse(userPreference.getId(), userPreference.getUserId(),
        BinanceApiKeyMasker.mask(userPreference.getUserBinanceApiKey()),
        BinanceApiKeyMasker.mask(userPreference.getUserBinanceApiSecret()),
        userPreference.getDisplayCurrency(), userPreference.getLanguage(),
        userPreference.getTheme());
  }
}
