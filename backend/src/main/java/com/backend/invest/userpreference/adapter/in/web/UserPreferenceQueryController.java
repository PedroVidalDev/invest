package com.backend.invest.userpreference.adapter.in.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.invest.userpreference.adapter.in.web.dto.UserPreferenceResponse;
import com.backend.invest.userpreference.adapter.in.web.dto.UserPreferenceSummaryResponse;
import com.backend.invest.userpreference.domain.port.in.UserPreferenceQueryUseCase;

@RestController
@RequestMapping("/userpreference")
public class UserPreferenceQueryController {

    private final UserPreferenceQueryUseCase userPreferenceQuery;

    public UserPreferenceQueryController(UserPreferenceQueryUseCase userPreferenceQuery) {
        this.userPreferenceQuery = userPreferenceQuery;
    }

    @GetMapping("/")
    public ResponseEntity<UserPreferenceResponse> get(@AuthenticationPrincipal String userId) {
        return ResponseEntity.ok(
                UserPreferenceResponse.from(userPreferenceQuery.findByUserId(userId))
        );
    }

    @GetMapping
    public ResponseEntity<List<UserPreferenceSummaryResponse>> list() {
        List<UserPreferenceSummaryResponse> body = userPreferenceQuery.findAll()
                .stream()
                .map(UserPreferenceSummaryResponse::from)
                .toList();

        return ResponseEntity.ok(body);
    }
}
