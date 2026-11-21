package com.backend.invest.userAlert.domain.port.in;

import java.util.List;

import com.backend.invest.userAlert.domain.UserAlert;

public interface UserAlertQueryUseCase {
  UserAlert findByIdAndUserId(String id, String userId);

  List<UserAlert> findByUserId(String userId);
}
