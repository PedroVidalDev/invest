package com.backend.invest.user.adapter.out.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaSpringRepository extends JpaRepository <UserJpaEntity, String> {
    boolean existsByEmail(String email);

    Optional<UserJpaEntity> findByEmail(String email);
}
