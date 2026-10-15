package com.backend.invest.user.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.invest.user.adapter.in.web.dto.CreateUserRequest;
import com.backend.invest.user.adapter.in.web.dto.UserResponse;
import com.backend.invest.user.domain.port.in.CreateUserUseCase;

@RestController 
@RequestMapping("/users")
public class UserCreateController {
    private final CreateUserUseCase createUser;

    public UserCreateController (CreateUserUseCase createUser) {
        this.createUser = createUser;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(
        @RequestBody CreateUserRequest request
    ) {
        return ResponseEntity.ok(
            UserResponse.from(
                createUser.create(request.toDomain())
            )
        );
    }
}
