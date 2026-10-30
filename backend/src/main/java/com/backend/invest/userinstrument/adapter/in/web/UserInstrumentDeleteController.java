package com.backend.invest.userinstrument.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.invest.userinstrument.domain.port.in.DeleteUserInstrumentUseCase;

@RestController
@RequestMapping("/userInstrument")
public class UserInstrumentDeleteController {
    private final DeleteUserInstrumentUseCase deleteUserInstrument;

    public UserInstrumentDeleteController(DeleteUserInstrumentUseCase deleteUserInstrument) {
        this.deleteUserInstrument = deleteUserInstrument;
    }

    @DeleteMapping("/{userId}/{instrumentId}")
    public ResponseEntity<Void> delete(@PathVariable String userId, @PathVariable String instrumentId) {
        deleteUserInstrument.delete(userId, instrumentId);

        return ResponseEntity.noContent().build();
    }
}
