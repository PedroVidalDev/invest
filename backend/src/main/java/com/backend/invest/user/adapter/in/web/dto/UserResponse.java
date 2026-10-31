package com.backend.invest.user.adapter.in.web.dto;

import java.time.LocalDateTime;

import com.backend.invest.user.domain.CategoryType;
import com.backend.invest.user.domain.User;

public record UserResponse (
    String id,
    String name,
    String email,
    String photo,
    boolean active,
    CategoryType category,
    LocalDateTime verifiedAt
) {
    
    public static UserResponse from (User user) {
        return new UserResponse(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getPhoto(),
            user.getActive(),
            user.getCategory(),
            user.getVerifiedAt()
        );
    }
}
