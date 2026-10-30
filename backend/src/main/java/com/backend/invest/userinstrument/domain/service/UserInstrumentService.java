package com.backend.invest.userinstrument.domain.service;

import java.time.LocalDateTime;
import java.util.List;

import com.backend.invest.userinstrument.domain.UserInstrument;
import com.backend.invest.userinstrument.domain.port.exception.UserInstrumentAlreadyExistsException;
import com.backend.invest.userinstrument.domain.port.in.CreateUserInstrumentUseCase;
import com.backend.invest.userinstrument.domain.port.in.DeleteUserInstrumentUseCase;
import com.backend.invest.userinstrument.domain.port.in.UserInstrumentQueryUseCase;
import com.backend.invest.userinstrument.domain.port.in.command.CreateUserInstrumentCommand;
import com.backend.invest.userinstrument.domain.port.out.UserInstrumentRepositoryPort;

public class UserInstrumentService implements CreateUserInstrumentUseCase, UserInstrumentQueryUseCase, DeleteUserInstrumentUseCase {
    private final UserInstrumentRepositoryPort repository;

    public UserInstrumentService (UserInstrumentRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public UserInstrument create(CreateUserInstrumentCommand command) {
        List<UserInstrument> existingUserInstrument = findByUserId(command.userId());

        if(existingUserInstrument.stream().anyMatch(ui -> ui.getInstrumentId().equals(command.instrumentId())))
            throw new UserInstrumentAlreadyExistsException(command.userId());

        UserInstrument userInstrument = new UserInstrument(
            null,
            command.userId(),
            command.instrumentId(),
            LocalDateTime.now(),
            LocalDateTime.now()
        );

        return repository.save(userInstrument);
    }

    @Override 
    public List<UserInstrument> findByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }
}
