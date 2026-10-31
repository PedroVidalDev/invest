package com.backend.invest.userinstrument.domain.service;

import java.time.LocalDateTime;
import java.util.List;

import com.backend.invest.user.domain.port.in.UserQueryUseCase;
import com.backend.invest.userinstrument.domain.UserInstrument;
import com.backend.invest.userinstrument.domain.exception.UserInstrumentAlreadyExistsException;
import com.backend.invest.userinstrument.domain.exception.UserInstrumentNotFoundException;
import com.backend.invest.userinstrument.domain.port.in.CreateUserInstrumentUseCase;
import com.backend.invest.userinstrument.domain.port.in.DeleteUserInstrumentUseCase;
import com.backend.invest.userinstrument.domain.port.in.UserInstrumentQueryUseCase;
import com.backend.invest.userinstrument.domain.port.in.command.CreateUserInstrumentCommand;
import com.backend.invest.userinstrument.domain.port.out.UserInstrumentRepositoryPort;

public class UserInstrumentService implements CreateUserInstrumentUseCase, UserInstrumentQueryUseCase, DeleteUserInstrumentUseCase {
    private final UserInstrumentRepositoryPort repository;
    private final UserQueryUseCase userQuery;

    public UserInstrumentService (UserInstrumentRepositoryPort repository, UserQueryUseCase userQuery) {
        this.repository = repository;
        this.userQuery = userQuery;
    }

    @Override
    public UserInstrument create(CreateUserInstrumentCommand command) {
        userQuery.findById(command.userId());

        if(repository.existsByUserIdAndInstrumentId(command.userId(), command.instrumentId()))
            throw new UserInstrumentAlreadyExistsException(command.userId(), command.instrumentId());

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
        userQuery.findById(userId);

        return repository.findByUserId(userId);
    }

    @Override
    public void delete(String userId, String instrumentId) {
        if(!repository.existsByUserIdAndInstrumentId(userId, instrumentId))
            throw new UserInstrumentNotFoundException(userId, instrumentId);

        repository.deleteByUserIdAndInstrumentId(userId, instrumentId);
    }
}
