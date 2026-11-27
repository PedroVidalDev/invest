package com.backend.invest.userpreference.adapter.in.web.dto;

import com.backend.invest.userpreference.domain.DisplayCurrencyType;
import com.backend.invest.userpreference.domain.LanguageType;
import com.backend.invest.userpreference.domain.ThemeType;

public record CreateUserPreferenceRequest(
    DisplayCurrencyType displayCurrency,
    LanguageType language,
    ThemeType theme
) {
}
