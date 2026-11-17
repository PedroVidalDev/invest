package com.backend.invest.token.adapter.out.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenJpaSpringRepository extends JpaRepository<TokenJpaEntity, String> {
    Optional<TokenJpaEntity> findByUserId(String userId);
}
