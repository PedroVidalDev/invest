package com.backend.invest.userpreference.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.backend.invest.userpreference.domain.UserPreference;
import com.backend.invest.userpreference.domain.port.out.UserPreferenceRepositoryPort;

@Repository 
public class UserPreferenceJpaRepository implements UserPreferenceRepositoryPort{
    private final UserPreferenceJpaSpringRepository springRepository;

    public UserPreferenceJpaRepository (UserPreferenceJpaSpringRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public UserPreference save(UserPreference userPreference) {
        return springRepository.save(UserPreferenceJpaEntity.from(userPreference)).toDomain();
    }

    @Override
    public Optional<UserPreference> findById(String id) {
        return springRepository.findById(id).map(UserPreferenceJpaEntity::toDomain);
    }

    @Override
    public List<UserPreference> findAll() {
        return springRepository.findAll().stream().map(UserPreferenceJpaEntity::toDomain).toList();
    }
}
