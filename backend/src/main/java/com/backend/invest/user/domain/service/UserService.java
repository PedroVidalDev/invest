package com.backend.invest.user.domain.service;

import java.util.List;
import java.util.Optional;

import com.backend.invest.user.domain.User;
import com.backend.invest.user.domain.port.in.CreateUserUseCase;
import com.backend.invest.user.domain.port.in.UserQueryUseCase;
import com.backend.invest.user.domain.port.out.UserRepositoryPort;

public class UserService implements CreateUserUseCase, UserQueryUseCase {
    private final UserRepositoryPort repository;

    public UserService (UserRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public Optional<User> findById(String id) {
        return repository.findById(id);
    }

    @Override 
    public List<User> findAll() {
        return repository.findAll();
    }
}
