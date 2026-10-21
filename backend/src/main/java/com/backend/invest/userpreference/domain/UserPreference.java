package com.backend.invest.userpreference.domain;

import java.time.LocalDateTime;

public class UserPreference {
    private String id;
    private String userId;
    private String cashInstrumentId;
    private String displayCurrency;
    private LanguageType language;
    private ThemeType theme;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserPreference (String id, String userId, String cashInstrumentId, String displayCurrency, LanguageType language, ThemeType theme, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.cashInstrumentId = cashInstrumentId;
        this.displayCurrency = displayCurrency;
        this.language = language;
        this.theme = theme;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return this.id;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getCashInstrumentId() {
        return this.cashInstrumentId;
    }

    public String getDisplayCurrency() {
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
