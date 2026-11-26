package com.backend.invest.token.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.invest.token.adapter.in.web.dto.TokenSummaryResponse;
import com.backend.invest.token.domain.port.in.TokenQueryUseCase;

@RestController
@RequestMapping("/token")
public class TokenQueryController {
    private final TokenQueryUseCase tokenQuery;

    public TokenQueryController(TokenQueryUseCase tokenQuery) {
        this.tokenQuery = tokenQuery;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<TokenSummaryResponse> findByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(
            TokenSummaryResponse.from(tokenQuery.findByUserId(userId))
        );
    }
}
