package com.backend.invest.user.adapter.in.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.invest.instrument.adapter.in.web.dto.InstrumentSummaryResponse;
import com.backend.invest.user.adapter.in.web.dto.UserResponse;
import com.backend.invest.user.adapter.in.web.dto.UserSummaryResponse;
import com.backend.invest.user.domain.port.in.CreateUserUseCase;
import com.backend.invest.user.domain.port.in.UserQueryUseCase;

@RestController 
@RequestMapping("/users")
public class UserQueryController {

    private final UserQueryUseCase userQuery;

    public UserQueryController (UserQueryUseCase userQuery) {
        this.userQuery = userQuery;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> get(@PathVariable String id) {
        return userQuery.findById(id)
                .map(UserResponse::from)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserSummaryResponse>> list() {
        List<UserSummaryResponse> body = userQuery.findAll()
                .stream()
                .map(UserSummaryResponse::from)
                .toList();

        return ResponseEntity.ok(body);
    }
}