package com.backend.invest.user.domain.port.in.command;

public record CreateUserCommand(
    String name, 
    String email, 
    String password,
    String photo
) {}
