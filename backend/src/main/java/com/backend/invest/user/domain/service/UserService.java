package com.backend.invest.user.domain.service;

import java.util.List;

import com.backend.invest.user.domain.User;
import com.backend.invest.user.domain.exception.UserAlreadyExistsException;
import com.backend.invest.user.domain.exception.UserNotFoundException;
import com.backend.invest.user.domain.port.in.CreateUserUseCase;
import com.backend.invest.user.domain.port.in.DeleteUserUseCase;
import com.backend.invest.user.domain.port.in.ToggleActiveUseCase;
import com.backend.invest.user.domain.port.in.UpdateUserUseCase;
import com.backend.invest.user.domain.port.in.UserQueryUseCase;
import com.backend.invest.user.domain.port.in.command.UpdateUserCommand;
import com.backend.invest.user.domain.port.out.UserRepositoryPort;

public class UserService
        implements CreateUserUseCase, UserQueryUseCase, UpdateUserUseCase, DeleteUserUseCase, ToggleActiveUseCase {
    private final UserRepositoryPort repository;

    public UserService(UserRepositoryPort repository) {
        this.repository = repository;
    }

    private User getUserOrThrow(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User create(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail());
        }

        return repository.save(user);
    }

    @Override
    public User findById(String id) {
        return getUserOrThrow(id);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User update(String id, UpdateUserCommand command) {
        User user = getUserOrThrow(id);

        if (!user.getEmail().equals(command.email()) && repository.existsByEmail(command.email())) {
            throw new UserAlreadyExistsException(command.email());
        }

        user.updateProfile(command.name(), command.email(), command.photo());
        return repository.save(user);
    }

    @Override
    public User toggleActive(String id) {
        User user = getUserOrThrow(id);

        user.toggleActive();

        return repository.save(user);
    }

    @Override
    public void delete(String id) {
        getUserOrThrow(id);

        repository.delete(id);
    }
}
