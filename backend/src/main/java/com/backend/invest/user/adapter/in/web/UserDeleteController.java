package com.backend.invest.user.adapter.in.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.invest.user.domain.port.in.DeleteUserUseCase;

@RestController
@RequestMapping("/users")
public class UserDeleteController {
    private final DeleteUserUseCase deleteUserUseCase;

    public UserDeleteController(DeleteUserUseCase deleteUserUseCase) {
        this.deleteUserUseCase = deleteUserUseCase;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        deleteUserUseCase.delete(id);
    }
}
