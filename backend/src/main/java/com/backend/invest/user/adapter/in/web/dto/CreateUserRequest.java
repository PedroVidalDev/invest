package com.backend.invest.user.adapter.in.web.dto;

import java.time.LocalDateTime;

import com.backend.invest.user.domain.CategoryType;
import com.backend.invest.user.domain.User;

public record CreateUserRequest(
        String name,
        String email,
        String password,
        String photo,
        boolean active,
        CategoryType category,
        LocalDateTime verifiedAt) {

    public User toDomain() {
        return new User (
            null,
            name,
            email,
            password,
            photo,
            active,
            category,
            verifiedAt,
            null,
            null
        );
    }
}
