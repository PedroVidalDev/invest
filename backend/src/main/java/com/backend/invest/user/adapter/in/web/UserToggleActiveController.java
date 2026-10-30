package com.backend.invest.user.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.invest.user.adapter.in.web.dto.UserResponse;
import com.backend.invest.user.domain.port.in.ToggleActiveUseCase;

@RestController
@RequestMapping("/users")
public class UserToggleActiveController {
    private final ToggleActiveUseCase toggleActive;

    public UserToggleActiveController(ToggleActiveUseCase toggleActive) {
        this.toggleActive = toggleActive;
    }

    @PatchMapping("/{id}/toggle-active")
    public ResponseEntity<UserResponse> toggleActive(@PathVariable String id) {
        return ResponseEntity.ok(
            UserResponse.from(
                toggleActive.toggleActive(id)
            )
        );
    }
}
