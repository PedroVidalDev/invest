package com.backend.invest.userpreference.domain.port.in;

import com.backend.invest.userpreference.domain.UserPreference;

public interface CreateUserPreferenceUseCase {
    UserPreference create(UserPreference userPreference);
}
