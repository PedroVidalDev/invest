package com.backend.invest.userpreference.domain.service;

import java.util.List;
import java.util.Optional;

import com.backend.invest.userpreference.domain.UserPreference;
import com.backend.invest.userpreference.domain.port.in.CreateUserPreferenceUseCase;
import com.backend.invest.userpreference.domain.port.in.UserPreferenceQueryUseCase;
import com.backend.invest.userpreference.domain.port.out.UserPreferenceRepositoryPort;

public class UserPreferenceService implements CreateUserPreferenceUseCase, UserPreferenceQueryUseCase {
    private final UserPreferenceRepositoryPort repository;

    public UserPreferenceService(UserPreferenceRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public UserPreference create(UserPreference userPreference) {
        return repository.save(userPreference);
    }

    @Override
    public Optional<UserPreference> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<UserPreference> findAll() {
        return repository.findAll();
    }
}
