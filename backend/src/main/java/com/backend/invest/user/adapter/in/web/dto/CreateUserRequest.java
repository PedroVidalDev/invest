package com.backend.invest.user.adapter.in.web.dto;

import com.backend.invest.user.domain.port.in.command.CreateUserCommand;

public record CreateUserRequest(
    String name, 
    String email, 
    String password, 
    String photo
) {
    public CreateUserCommand toCommand() {
        return new CreateUserCommand(name, email, password, photo);
    }
}
