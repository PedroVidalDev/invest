package com.backend.invest.userpreference.domain.port.out;

import java.util.List;

import com.backend.invest.userpreference.domain.UserPreference;

public interface UserPreferenceRepositoryPort {
    UserPreference save (UserPreference userPreference);

    UserPreference findByUserId(String userid);

    List<UserPreference> findAll();
}
