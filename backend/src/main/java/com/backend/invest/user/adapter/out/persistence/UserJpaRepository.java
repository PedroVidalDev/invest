package com.backend.invest.user.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.backend.invest.user.domain.port.out.UserRepositoryPort;
import com.backend.invest.user.domain.User;

@Repository 
public class UserJpaRepository implements UserRepositoryPort {
    private final UserJpaSpringRepository springRepository;

    public UserJpaRepository(UserJpaSpringRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public User save(User user) {
        return springRepository.save(UserJpaEntity.from(user)).toDomain();
    }

    @Override
    public Optional<User> findById(String id) {
        return springRepository.findById(id).map(UserJpaEntity::toDomain);
    }

    @Override
    public List<User> findAll() {
        return springRepository.findAll().stream().map(UserJpaEntity::toDomain).toList();
    }

    @Override
    public boolean existsByEmail(String email) {
        return springRepository.existsByEmail(email);
    }

    @Override
    public void delete(String id) {
        springRepository.deleteById(id);
    }
}
