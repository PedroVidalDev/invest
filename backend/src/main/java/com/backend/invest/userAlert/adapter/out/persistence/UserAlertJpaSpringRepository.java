package com.backend.invest.userAlert.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserAlertJpaSpringRepository extends JpaRepository<UserAlertJpaEntity, String> {
  Optional<UserAlertJpaEntity> findByIdAndUserId(String id, String userId);

  List<UserAlertJpaEntity> findByUserId(String userId);

  boolean existsByUserIdAndInstrumentId(String userId, String instrumentId);

  List<UserAlertJpaEntity> findByActiveTrue();

  @Transactional
  void deleteByUserIdAndInstrumentId(String userId, String instrumentId);
}
