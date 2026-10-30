package com.backend.invest.user.adapter.in.web.dto;


import com.backend.invest.user.domain.User;

public record UserSummaryResponse (
    String id,
    String name,
    String email,
    String photo,
    boolean active
) {
    
    public static UserSummaryResponse from (User user) {
        return new UserSummaryResponse(
            user.getId(),
            user.getName(),
            user.getEmail(),
            user.getPhoto(),
            user.getActive()
        );
    }
}
