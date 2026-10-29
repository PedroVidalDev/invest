package com.backend.invest.user.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaSpringRepository extends JpaRepository <UserJpaEntity, String> {
    boolean existsByEmail(String email);
}
