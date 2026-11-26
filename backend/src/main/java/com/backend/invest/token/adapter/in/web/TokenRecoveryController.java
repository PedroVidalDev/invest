package com.backend.invest.token.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.invest.token.adapter.in.web.dto.RecoveryTokenRequest;
import com.backend.invest.token.domain.ServiceType;
import com.backend.invest.token.domain.port.in.CreateTokenUseCase;
import com.backend.invest.token.domain.port.in.command.CreateTokenCommand;
import com.backend.invest.user.domain.User;
import com.backend.invest.user.domain.exception.UserNotFoundException;
import com.backend.invest.user.domain.port.in.UserQueryUseCase;

@RestController
@RequestMapping("/token")
public class TokenRecoveryController {
    private final CreateTokenUseCase createToken;
    private final UserQueryUseCase userQuery;

    public TokenRecoveryController(CreateTokenUseCase createToken, UserQueryUseCase userQuery) {
        this.createToken = createToken;
        this.userQuery = userQuery;
    }

    /**
     * Always answers 202, whether the email exists or not,
     * so the endpoint can't be used to discover registered emails.
     */
    @PostMapping("/recovery")
    public ResponseEntity<Void> recovery(@RequestBody RecoveryTokenRequest request) {
        try {
            User user = userQuery.findByEmail(request.email());
            createToken.create(new CreateTokenCommand(user.getId(), ServiceType.RECOVERY));
        } catch (UserNotFoundException ignored) {
            // Intentionally silent
        }

        return ResponseEntity.accepted().build();
    }
}
