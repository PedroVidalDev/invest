package com.backend.invest.token.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.invest.token.adapter.in.web.dto.VerifyTokenRequest;
import com.backend.invest.token.domain.port.in.VerifyTokenUseCase;

@RestController
@RequestMapping("/token")
public class TokenVerifyController {
    private final VerifyTokenUseCase verifyToken;

    public TokenVerifyController(VerifyTokenUseCase verifyToken) {
        this.verifyToken = verifyToken;
    }

    @PostMapping("/verify")
    public ResponseEntity<Void> verify(@RequestBody VerifyTokenRequest request) {
        verifyToken.verifyToken(request.userId(), request.code());

        return ResponseEntity.noContent().build();
    }
}
