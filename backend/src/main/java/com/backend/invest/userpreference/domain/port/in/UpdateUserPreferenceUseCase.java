package com.backend.invest.userpreference.domain.port.in;

import com.backend.invest.userpreference.domain.UserPreference;
import com.backend.invest.userpreference.domain.port.in.command.UpdateUserPreferenceCommand;

public interface UpdateUserPreferenceUseCase {
    UserPreference update(String userId, UpdateUserPreferenceCommand userPreference);
}
