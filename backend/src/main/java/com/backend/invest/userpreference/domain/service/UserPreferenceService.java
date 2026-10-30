package com.backend.invest.userpreference.domain.service;

import java.time.LocalDateTime;
import java.util.List;

import com.backend.invest.user.domain.port.in.UserQueryUseCase;
import com.backend.invest.userpreference.domain.UserPreference;
import com.backend.invest.userpreference.domain.exception.UserPreferenceAlreadyExistsException;
import com.backend.invest.userpreference.domain.exception.UserPreferenceNotFoundException;
import com.backend.invest.userpreference.domain.port.in.CreateUserPreferenceUseCase;
import com.backend.invest.userpreference.domain.port.in.UpdateUserPreferenceUseCase;
import com.backend.invest.userpreference.domain.port.in.UserPreferenceQueryUseCase;
import com.backend.invest.userpreference.domain.port.in.command.CreateUserPreferenceCommand;
import com.backend.invest.userpreference.domain.port.in.command.UpdateUserPreferenceCommand;
import com.backend.invest.userpreference.domain.port.out.UserPreferenceRepositoryPort;

public class UserPreferenceService
        implements CreateUserPreferenceUseCase, UserPreferenceQueryUseCase, UpdateUserPreferenceUseCase {
    private final UserPreferenceRepositoryPort repository;
    private final UserQueryUseCase userQuery;

    public UserPreferenceService(UserPreferenceRepositoryPort repository, UserQueryUseCase userQuery) {
        this.repository = repository;
        this.userQuery = userQuery;
    }

    @Override
    public UserPreference create(CreateUserPreferenceCommand command) {
        userQuery.findById(command.userId());

        if (repository.findByUserId(command.userId()) != null) {
            throw new UserPreferenceAlreadyExistsException(command.userId());
        }

        UserPreference userPreference = new UserPreference(
                null,

                command.userId(),
                command.displayCurrency(),
                command.language(),
                command.theme(),

                LocalDateTime.now(),
                LocalDateTime.now());

        return repository.save(userPreference);
    }

    @Override
    public UserPreference update(String userId, UpdateUserPreferenceCommand command) {
        userQuery.findById(userId);
        UserPreference userPreference = findByUserId(userId);

        UserPreference updatedUserPreference = new UserPreference(
                userPreference.getId(),
                userPreference.getUserId(),

                command.displayCurrency(),
                command.language(),
                command.theme(),

                userPreference.getCreatedAt(),
                LocalDateTime.now());

        return repository.save(updatedUserPreference);
    }

    @Override
    public UserPreference findByUserId(String userId) {
        UserPreference userPreference = repository.findByUserId(userId);

        if (userPreference == null) {
            throw new UserPreferenceNotFoundException(userId);
        }

        return userPreference;
    }

    @Override
    public List<UserPreference> findAll() {
        return repository.findAll();
    }
}
