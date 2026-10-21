package com.backend.invest.userinstrument.adapter.in.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.invest.userinstrument.adapter.in.web.dto.UserInstrumentResponse;
import com.backend.invest.userinstrument.adapter.in.web.dto.UserInstrumentSummaryResponse;
import com.backend.invest.userinstrument.domain.port.in.UserInstrumentQueryUseCase;

@RestController
@RequestMapping("/userInstrument")
public class UserInstrumentQueryController {
    private final UserInstrumentQueryUseCase userInstrumentQuery;

    public UserInstrumentQueryController(UserInstrumentQueryUseCase userInstrumentQuery) {
        this.userInstrumentQuery = userInstrumentQuery;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInstrumentResponse> get(@PathVariable String id) {
        return userInstrumentQuery.findById(id)
                .map(UserInstrumentResponse::from)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserInstrumentSummaryResponse>> list() {
        List<UserInstrumentSummaryResponse> body = userInstrumentQuery.findAll()
                .stream()
                .map(UserInstrumentSummaryResponse::from)
                .toList();

        return ResponseEntity.ok(body);
    }
}
