package com.backend.invest.token.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.invest.token.adapter.in.web.dto.CreateTokenRequest;
import com.backend.invest.token.adapter.in.web.dto.TokenResponse;
import com.backend.invest.token.domain.port.in.CreateTokenUseCase;

@RestController
@RequestMapping("/token")
public class TokenCreateController {
    private final CreateTokenUseCase createToken;

    public TokenCreateController(CreateTokenUseCase createToken) {
        this.createToken = createToken;
    }

    @PostMapping
    public ResponseEntity<TokenResponse> create(@RequestBody CreateTokenRequest request) {
        return ResponseEntity.ok(
            TokenResponse.from(
                createToken.create(request.toCommand())
            )
        );
    }
}
