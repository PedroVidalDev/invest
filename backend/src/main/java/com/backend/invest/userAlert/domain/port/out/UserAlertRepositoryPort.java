package com.backend.invest.userAlert.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.backend.invest.userAlert.domain.UserAlert;

public interface UserAlertRepositoryPort {
  UserAlert save(UserAlert userAlert);

  Optional<UserAlert> findById(String id);

  Optional<UserAlert> findByIdAndUserId(String id, String userId);

  List<UserAlert> findByUserId(String userId);

  boolean existsByUserIdAndInstrumentId(String userId, String instrumentId);

  void deleteByUserIdAndInstrumentId(String userId, String instrumentId);

  List<UserAlert> findByActiveTrue();
}
