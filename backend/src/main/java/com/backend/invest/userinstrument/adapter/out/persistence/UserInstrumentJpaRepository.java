package com.backend.invest.userinstrument.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.backend.invest.userinstrument.domain.UserInstrument;
import com.backend.invest.userinstrument.domain.port.out.UserInstrumentRepositoryPort;

@Repository
public class UserInstrumentJpaRepository implements UserInstrumentRepositoryPort {
    private final UserInstrumentJpaSpringRepository springRepository;

    public UserInstrumentJpaRepository(UserInstrumentJpaSpringRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public UserInstrument save(UserInstrument userInstrument) {
        return springRepository.save(UserInstrumentJpaEntity.from(userInstrument)).toDomain();
    }

    @Override
    public Optional<UserInstrument> findById(String id) {
        return springRepository.findById(id).map(UserInstrumentJpaEntity::toDomain);
    }

    @Override
    public List<UserInstrument> findAll() {
        return springRepository.findAll().stream().map(UserInstrumentJpaEntity::toDomain).toList();
    }
}
