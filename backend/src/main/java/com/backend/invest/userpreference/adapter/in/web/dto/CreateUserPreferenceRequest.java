package com.backend.invest.userpreference.adapter.in.web.dto;

import com.backend.invest.userpreference.domain.LanguageType;
import com.backend.invest.userpreference.domain.ThemeType;
import com.backend.invest.userpreference.domain.UserPreference;

public record CreateUserPreferenceRequest(
    String userId,
    String cashInstrumentId,
    String displayCurrency,
    LanguageType language,
    ThemeType theme
) {

    public UserPreference toDomain() {
        return new UserPreference (
            null,
            userId,
            cashInstrumentId,
            displayCurrency,
            language,
            theme,
            null,
            null
        );
    }
}
