package com.backend.invest.userpreference.domain.port.in;

import java.util.List;
import com.backend.invest.userpreference.domain.UserPreference;

public interface UserPreferenceQueryUseCase {
    UserPreference findByUserId(String id);

    List<UserPreference> findAll();

}
