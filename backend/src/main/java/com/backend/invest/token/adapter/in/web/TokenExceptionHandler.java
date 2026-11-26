package com.backend.invest.token.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.backend.invest.token.domain.exception.InvalidTokenException;
import com.backend.invest.token.domain.exception.TokenAttemptsExceededException;
import com.backend.invest.token.domain.exception.TokenExpiredException;
import com.backend.invest.token.domain.exception.TokenNotFoundException;
import com.backend.invest.token.domain.exception.TokenResendCooldownException;
import com.backend.invest.user.adapter.in.web.dto.ErrorResponse;

@RestControllerAdvice
public class TokenExceptionHandler {

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(TokenNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> handleInvalid(InvalidTokenException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ErrorResponse> handleExpired(TokenExpiredException e) {
        return ResponseEntity.status(HttpStatus.GONE)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(TokenAttemptsExceededException.class)
    public ResponseEntity<ErrorResponse> handleAttemptsExceeded(TokenAttemptsExceededException e) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(TokenResendCooldownException.class)
    public ResponseEntity<ErrorResponse> handleResendCooldown(TokenResendCooldownException e) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .body(new ErrorResponse(e.getMessage()));
    }
}
