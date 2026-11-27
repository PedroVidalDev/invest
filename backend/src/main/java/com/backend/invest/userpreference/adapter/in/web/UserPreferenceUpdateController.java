package com.backend.invest.userpreference.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.invest.userpreference.adapter.in.web.dto.UpdateUserPreferenceRequest;
import com.backend.invest.userpreference.adapter.in.web.dto.UserPreferenceResponse;
import com.backend.invest.userpreference.domain.port.in.UpdateUserPreferenceUseCase;

@RestController
@RequestMapping("/userpreference")
public class UserPreferenceUpdateController {
    private final UpdateUserPreferenceUseCase updateUserPreference;

    public UserPreferenceUpdateController(UpdateUserPreferenceUseCase updateUserPreference) {
        this.updateUserPreference = updateUserPreference;
    }

    @PutMapping("/")
    public ResponseEntity<UserPreferenceResponse> update(
        @AuthenticationPrincipal String userId,
        @RequestBody UpdateUserPreferenceRequest request
    ) {
        return ResponseEntity.ok(
            UserPreferenceResponse.from(
                updateUserPreference.update(userId, request.toCommand())
            )
        );
    }
}
