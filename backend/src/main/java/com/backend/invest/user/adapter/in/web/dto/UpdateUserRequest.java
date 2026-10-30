package com.backend.invest.user.adapter.in.web.dto;

import com.backend.invest.user.domain.port.in.command.UpdateUserCommand;

public record UpdateUserRequest(
        String name,
        String email,
        String photo
) {
    public UpdateUserCommand toCommand() {
        return new UpdateUserCommand(name, email, photo);
    }
}
