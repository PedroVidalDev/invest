package com.backend.invest.user.domain.port.in;

import com.backend.invest.user.domain.User;
import com.backend.invest.user.domain.port.in.command.UpdateUserCommand;

public interface UpdateUserUseCase {
    User update(String id, UpdateUserCommand command);
}
