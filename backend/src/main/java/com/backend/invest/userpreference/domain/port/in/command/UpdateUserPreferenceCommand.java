package com.backend.invest.userpreference.domain.port.in.command;

import com.backend.invest.userpreference.domain.DisplayCurrencyType;
import com.backend.invest.userpreference.domain.LanguageType;
import com.backend.invest.userpreference.domain.ThemeType;

public record UpdateUserPreferenceCommand(
    DisplayCurrencyType displayCurrency,
    LanguageType language,
    ThemeType theme
) {}
