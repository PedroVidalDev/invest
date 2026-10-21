package com.backend.invest.userpreference.adapter.in.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    public ResponseEntity<UserPreferenceResponse> get(@PathVariable String id) {
        return userPreferenceQuery.findById(id)
                .map(UserPreferenceResponse::from)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
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
