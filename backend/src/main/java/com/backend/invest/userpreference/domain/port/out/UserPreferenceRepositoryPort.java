package com.backend.invest.userpreference.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.backend.invest.userpreference.domain.UserPreference;

public interface UserPreferenceRepositoryPort {
    UserPreference save (UserPreference userPreference);

    Optional<UserPreference> findById (String id);

    List<UserPreference> findAll();
}
