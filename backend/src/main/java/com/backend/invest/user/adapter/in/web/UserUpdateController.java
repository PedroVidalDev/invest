package com.backend.invest.user.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.invest.user.adapter.in.web.dto.CreateUserRequest;
import com.backend.invest.user.adapter.in.web.dto.UserResponse;
import com.backend.invest.user.domain.port.in.UpdateUserUseCase;

@RestController 
@RequestMapping ("/users")
public class UserUpdateController {
    private final UpdateUserUseCase updateUser;

    public UserUpdateController (UpdateUserUseCase updateUser) {
        this.updateUser = updateUser;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(
        @PathVariable String id,
        @RequestBody CreateUserRequest request
    ) {
        return ResponseEntity.ok(
            UserResponse.from(
                updateUser.update(request.toDomain())
            )
        );  
    }
}
