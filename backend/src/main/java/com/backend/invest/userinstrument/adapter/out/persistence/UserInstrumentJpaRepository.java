package com.backend.invest.userinstrument.adapter.out.persistence;

import java.util.List;

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
    public List<UserInstrument> findByUserId(String userId) {
        return springRepository.findByUserId(userId).stream().map(UserInstrumentJpaEntity::toDomain).toList();
    }

    @Override
    public boolean existsByUserIdAndInstrumentId(String userId, String instrumentId) {
        return springRepository.existsByUserIdAndInstrumentId(userId, instrumentId);
    }

    @Override
    public void deleteByUserIdAndInstrumentId(String userId, String instrumentId) {
        springRepository.deleteByUserIdAndInstrumentId(userId, instrumentId);
    }
}
