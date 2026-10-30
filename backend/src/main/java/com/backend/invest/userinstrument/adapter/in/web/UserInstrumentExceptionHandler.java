package com.backend.invest.userinstrument.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.backend.invest.user.adapter.in.web.dto.ErrorResponse;
import com.backend.invest.userinstrument.domain.exception.UserInstrumentAlreadyExistsException;
import com.backend.invest.userinstrument.domain.exception.UserInstrumentNotFoundException;

@RestControllerAdvice
public class UserInstrumentExceptionHandler {

    @ExceptionHandler(UserInstrumentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(UserInstrumentNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(UserInstrumentAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExists(UserInstrumentAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(e.getMessage()));
    }
}
