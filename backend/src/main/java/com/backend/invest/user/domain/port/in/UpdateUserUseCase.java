package com.backend.invest.user.domain.port.in;

import com.backend.invest.user.domain.User;

public interface UpdateUserUseCase {
    User update(User user);
}
