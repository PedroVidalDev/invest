package com.backend.invest.userpreference.adapter.out.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserPreferenceJpaSpringRepository extends JpaRepository<UserPreferenceJpaEntity, String>{
    Optional<UserPreferenceJpaEntity> findByUserId(String userId);
}
