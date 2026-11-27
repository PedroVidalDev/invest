package com.backend.invest.userAlert.domain.port.in;

public interface DeleteUserAlertUseCase {
  void delete(String userId, String instrumentId);
}
