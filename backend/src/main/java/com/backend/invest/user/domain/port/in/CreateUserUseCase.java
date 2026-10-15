package com.backend.invest.user.domain.port.in;

import com.backend.invest.user.domain.User;

public interface CreateUserUseCase {
    User create(User user);
}
