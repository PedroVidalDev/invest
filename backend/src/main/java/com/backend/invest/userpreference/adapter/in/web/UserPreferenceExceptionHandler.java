package com.backend.invest.userpreference.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.backend.invest.user.adapter.in.web.dto.ErrorResponse;
import com.backend.invest.userpreference.domain.exception.UserPreferenceAlreadyExistsException;
import com.backend.invest.userpreference.domain.exception.UserPreferenceNotFoundException;

@RestControllerAdvice
public class UserPreferenceExceptionHandler {

    @ExceptionHandler(UserPreferenceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(UserPreferenceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(UserPreferenceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExists(UserPreferenceAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(e.getMessage()));
    }
}
