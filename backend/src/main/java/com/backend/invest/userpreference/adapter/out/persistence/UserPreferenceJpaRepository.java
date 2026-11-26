package com.backend.invest.userpreference.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.backend.invest.userpreference.domain.UserPreference;
import com.backend.invest.userpreference.domain.port.out.UserPreferenceRepositoryPort;

@Repository
public class UserPreferenceJpaRepository implements UserPreferenceRepositoryPort{
    private final UserPreferenceJpaSpringRepository springRepository;
    private final BinanceApiKeyEncryptor apiKeyEncryptor;

    public UserPreferenceJpaRepository (UserPreferenceJpaSpringRepository springRepository, BinanceApiKeyEncryptor apiKeyEncryptor) {
        this.springRepository = springRepository;
        this.apiKeyEncryptor = apiKeyEncryptor;
    }

    @Override
    public UserPreference save(UserPreference userPreference) {
        String encryptedApiKey = apiKeyEncryptor.encrypt(userPreference.getUserBinanceApiKey());
        UserPreferenceJpaEntity saved = springRepository.save(UserPreferenceJpaEntity.from(userPreference, encryptedApiKey));
        return toDomain(saved);
    }

    @Override
    public UserPreference findByUserId(String userId) {
        return springRepository.findByUserId(userId).map(this::toDomain).orElse(null);
    }

    @Override
    public List<UserPreference> findAll() {
        return springRepository.findAll().stream().map(this::toDomain).toList();
    }

    private UserPreference toDomain(UserPreferenceJpaEntity entity) {
        String decryptedApiKey = apiKeyEncryptor.decrypt(entity.getUserBinanceApiKey());
        return entity.toDomain(decryptedApiKey);
    }
}