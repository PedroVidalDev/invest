package com.backend.invest.userpreference.adapter.in.web.dto;

import com.backend.invest.userpreference.domain.DisplayCurrencyType;
import com.backend.invest.userpreference.domain.LanguageType;
import com.backend.invest.userpreference.domain.ThemeType;
import com.backend.invest.userpreference.domain.port.in.command.UpdateUserPreferenceCommand;

public record UpdateUserPreferenceRequest(String userBinanceApiKey, String userBinanceApiSecret,
    DisplayCurrencyType displayCurrency, LanguageType language, ThemeType theme) {
  public UpdateUserPreferenceCommand toCommand() {
    return new UpdateUserPreferenceCommand(userBinanceApiKey, userBinanceApiSecret, displayCurrency,
        language, theme);
  }
}
