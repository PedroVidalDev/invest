package com.backend.invest.userAlert.domain.exception;

public class UserAlertAlreadyExistsException extends RuntimeException {
  public UserAlertAlreadyExistsException(String userId, String instrumentId) {
    super("Alert for instrument " + instrumentId + " already exists for user: " + userId);
  }
}
