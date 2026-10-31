package com.backend.invest.user.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.backend.invest.user.adapter.in.web.dto.ErrorResponse;
import com.backend.invest.user.domain.exception.UserAlreadyExistsException;
import com.backend.invest.user.domain.exception.UserInactiveException;
import com.backend.invest.user.domain.exception.UserNotFoundException;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExists(UserAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(UserInactiveException.class)
    public ResponseEntity<ErrorResponse> handleInactive(UserInactiveException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse(e.getMessage()));
    }
}
