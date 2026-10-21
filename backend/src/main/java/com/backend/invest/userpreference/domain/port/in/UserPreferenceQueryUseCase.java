package com.backend.invest.userpreference.domain.port.in;

import java.util.List;
import java.util.Optional;

import com.backend.invest.userpreference.domain.UserPreference;

public interface UserPreferenceQueryUseCase {
    Optional<UserPreference> findById(String id);

    List<UserPreference> findAll();
}
