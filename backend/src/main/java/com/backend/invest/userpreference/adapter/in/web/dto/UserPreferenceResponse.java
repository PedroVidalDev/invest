package com.backend.invest.userpreference.adapter.in.web.dto;

import com.backend.invest.userpreference.domain.DisplayCurrencyType;
import com.backend.invest.userpreference.domain.LanguageType;
import com.backend.invest.userpreference.domain.ThemeType;
import com.backend.invest.userpreference.domain.UserPreference;

public record UserPreferenceResponse(
        String id,
        String userId,
        DisplayCurrencyType displayCurrency,
        LanguageType language,
        ThemeType theme) {

    public static UserPreferenceResponse from(UserPreference userPreference) {
        return new UserPreferenceResponse(
                userPreference.getId(),
                userPreference.getUserId(),
                userPreference.getDisplayCurrency(),
                userPreference.getLanguage(),
                userPreference.getTheme());
    }
}
