package com.backend.invest.userinstrument.adapter.in.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.invest.userinstrument.adapter.in.web.dto.UserInstrumentSummaryResponse;
import com.backend.invest.userinstrument.domain.port.in.UserInstrumentQueryUseCase;

@RestController
@RequestMapping("/userInstrument")
public class UserInstrumentQueryController {
    private final UserInstrumentQueryUseCase userInstrumentQuery;

    public UserInstrumentQueryController(UserInstrumentQueryUseCase userInstrumentQuery) {
        this.userInstrumentQuery = userInstrumentQuery;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserInstrumentSummaryResponse>> list(@PathVariable String userId) {
        List<UserInstrumentSummaryResponse> body = userInstrumentQuery.findByUserId(userId)
                .stream()
                .map(UserInstrumentSummaryResponse::from)
                .toList();

        return ResponseEntity.ok(body);
    }
}
