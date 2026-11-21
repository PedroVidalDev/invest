package com.backend.invest.userAlert.domain.exception;

public class UserAlertNotFoundException extends RuntimeException {
  public UserAlertNotFoundException(String id) {
    super("User alert not found with id: " + id);
  }

  public UserAlertNotFoundException(String userId, String instrumentId) {
    super("Alert for instrument " + instrumentId + " not found for user: " + userId);
  }
}
