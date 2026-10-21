package com.backend.invest.userpreference.adapter.out.persistence;

import java.time.LocalDateTime;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.backend.invest.userpreference.domain.LanguageType;
import com.backend.invest.userpreference.domain.ThemeType;
import com.backend.invest.userpreference.domain.UserPreference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "UserPreference")
@Table(name = "tb_userpreference")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserPreferenceJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String userId;
    private String cashInstrumentId;
    private String displayCurrency;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private LanguageType language;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private ThemeType theme;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserPreferenceJpaEntity from(UserPreference userPreference) {
        return new UserPreferenceJpaEntity(
                userPreference.getId(),
                userPreference.getUserId(),
                userPreference.getCashInstrumentId(),
                userPreference.getDisplayCurrency(),
                userPreference.getLanguage(),
                userPreference.getTheme(),
                userPreference.getCreatedAt(),
                userPreference.getUpdatedAt());
    }

    public UserPreference toDomain() {
        return new UserPreference(
                id,
                userId,
                cashInstrumentId,
                displayCurrency,
                language,
                theme,
                createdAt,
                updatedAt);
    }
}
