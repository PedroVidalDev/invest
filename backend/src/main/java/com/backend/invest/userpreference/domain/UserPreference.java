package com.backend.invest.userpreference.domain;

import java.time.LocalDateTime;

public class UserPreference {
  private String id;
  private String userId;
  private String userBinanceApiKey;
  private String userBinanceApiSecret;
  private DisplayCurrencyType displayCurrency;
  private LanguageType language;
  private ThemeType theme;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public UserPreference(String id, String userId, String userBinanceApiKey,
      String userBinanceApiSecret, DisplayCurrencyType displayCurrency, LanguageType language,
      ThemeType theme, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    this.userId = userId;
    this.userBinanceApiKey = userBinanceApiKey;
    this.userBinanceApiSecret = userBinanceApiSecret;
    this.displayCurrency = displayCurrency;
    this.language = language;
    this.theme = theme;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public String getId() {
    return this.id;
  }

  public String getUserBinanceApiKey() {
    return this.userBinanceApiKey;
  }

  public String getUserBinanceApiSecret() {
    return this.userBinanceApiSecret;
  }

  public String getUserId() {
    return this.userId;
  }

  public DisplayCurrencyType getDisplayCurrency() {
    return this.displayCurrency;
  }

  public LanguageType getLanguage() {
    return this.language;
  }

  public ThemeType getTheme() {
    return this.theme;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return this.updatedAt;
  }
}
