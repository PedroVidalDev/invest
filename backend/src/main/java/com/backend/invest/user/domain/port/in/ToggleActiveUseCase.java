package com.backend.invest.user.domain.port.in;

import com.backend.invest.user.domain.User;

public interface ToggleActiveUseCase {
    User toggleActive(String id);
}
