package com.backend.invest.userAlert.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.backend.invest.user.adapter.in.web.dto.ErrorResponse;
import com.backend.invest.userAlert.domain.exception.UserAlertAlreadyExistsException;
import com.backend.invest.userAlert.domain.exception.UserAlertNotFoundException;

@RestControllerAdvice
public class UserAlertExceptionHandler {

  @ExceptionHandler(UserAlertNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFound(UserAlertNotFoundException e) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));
  }

  @ExceptionHandler(UserAlertAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleAlreadyExists(UserAlertAlreadyExistsException e) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(e.getMessage()));
  }
}
