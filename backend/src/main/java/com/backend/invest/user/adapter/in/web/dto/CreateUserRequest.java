package com.backend.invest.user.adapter.in.web.dto;

import com.backend.invest.user.domain.CategoryType;
import com.backend.invest.user.domain.User;

public record CreateUserRequest(
        String name,
        String email,
        String password,
        String photo) {

    public User toDomain() {
        return new User (
            null,
            name,
            email,
            password,
            photo,
            true,
            CategoryType.USER,
            null,
            null,
            null
        );
    }
}
