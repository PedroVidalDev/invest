package com.backend.invest.userpreference.domain.port.in;

import com.backend.invest.userpreference.domain.UserPreference;
import com.backend.invest.userpreference.domain.port.in.command.CreateUserPreferenceCommand;

public interface CreateUserPreferenceUseCase {
    UserPreference create(CreateUserPreferenceCommand userPreference);
}
