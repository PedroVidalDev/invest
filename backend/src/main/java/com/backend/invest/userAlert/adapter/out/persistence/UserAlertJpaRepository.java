package com.backend.invest.userAlert.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.backend.invest.userAlert.domain.UserAlert;
import com.backend.invest.userAlert.domain.port.out.UserAlertRepositoryPort;

@Repository
public class UserAlertJpaRepository implements UserAlertRepositoryPort {
  private final UserAlertJpaSpringRepository springRepository;

  public UserAlertJpaRepository(UserAlertJpaSpringRepository springRepository) {
    this.springRepository = springRepository;
  }

  @Override
  public UserAlert save(UserAlert userAlert) {
    return springRepository.save(UserAlertJpaEntity.from(userAlert)).toDomain();
  }

  @Override
  public Optional<UserAlert> findById(String id) {
    return springRepository.findById(id).map(UserAlertJpaEntity::toDomain);
  }

  @Override
  public Optional<UserAlert> findByIdAndUserId(String id, String userId) {
    return springRepository.findByIdAndUserId(id, userId).map(UserAlertJpaEntity::toDomain);
  }

  @Override
  public List<UserAlert> findByUserId(String userId) {
    return springRepository.findByUserId(userId).stream().map(UserAlertJpaEntity::toDomain)
        .toList();
  }

  @Override
  public boolean existsByUserIdAndInstrumentId(String userId, String instrumentId) {
    return springRepository.existsByUserIdAndInstrumentId(userId, instrumentId);
  }

  @Override
  public void deleteByUserIdAndInstrumentId(String userId, String instrumentId) {
    springRepository.deleteByUserIdAndInstrumentId(userId, instrumentId);
  }

  @Override
  public List<UserAlert> findByActiveTrue() {
    return springRepository.findByActiveTrue().stream().map(UserAlertJpaEntity::toDomain).toList();
  }
}
