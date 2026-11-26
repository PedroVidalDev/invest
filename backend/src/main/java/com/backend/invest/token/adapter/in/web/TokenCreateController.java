package com.backend.invest.token.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.invest.token.adapter.in.web.dto.CreateTokenRequest;
import com.backend.invest.token.adapter.in.web.dto.TokenResponse;
import com.backend.invest.token.domain.port.in.CreateTokenUseCase;
import com.backend.invest.user.domain.port.in.UserQueryUseCase;

@RestController
@RequestMapping("/token")
public class TokenCreateController {
    private final CreateTokenUseCase createToken;
    private final UserQueryUseCase userQuery;

    public TokenCreateController(CreateTokenUseCase createToken, UserQueryUseCase userQuery) {
        this.createToken = createToken;
        this.userQuery = userQuery;
    }

    @PostMapping
    public ResponseEntity<TokenResponse> create(@RequestBody CreateTokenRequest request) {
        userQuery.findById(request.userId());

        return ResponseEntity.ok(
            TokenResponse.from(
                createToken.create(request.toCommand())
            )
        );
    }
}
