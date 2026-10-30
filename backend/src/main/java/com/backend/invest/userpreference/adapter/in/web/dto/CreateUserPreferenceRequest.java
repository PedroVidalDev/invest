package com.backend.invest.userpreference.adapter.in.web.dto;

import com.backend.invest.userpreference.domain.DisplayCurrencyType;
import com.backend.invest.userpreference.domain.LanguageType;
import com.backend.invest.userpreference.domain.ThemeType;
import com.backend.invest.userpreference.domain.port.in.command.CreateUserPreferenceCommand;

public record CreateUserPreferenceRequest(
    String userId,
    DisplayCurrencyType displayCurrency,
    LanguageType language,
    ThemeType theme
) {
    public CreateUserPreferenceCommand toCommand() {
        return new CreateUserPreferenceCommand(userId, displayCurrency, language, theme);
    }
}
