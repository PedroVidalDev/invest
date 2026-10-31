package com.backend.invest.user.domain.port.in;

import com.backend.invest.user.domain.User;
import com.backend.invest.user.domain.port.in.command.CreateUserCommand;

public interface CreateUserUseCase {
    User create(CreateUserCommand user);
}
