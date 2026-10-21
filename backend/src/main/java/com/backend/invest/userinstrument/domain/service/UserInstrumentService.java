package com.backend.invest.userinstrument.domain.service;

import java.util.List;
import java.util.Optional;

import com.backend.invest.userinstrument.domain.UserInstrument;
import com.backend.invest.userinstrument.domain.port.in.CreateUserInstrumentUseCase;
import com.backend.invest.userinstrument.domain.port.in.UserInstrumentQueryUseCase;
import com.backend.invest.userinstrument.domain.port.out.UserInstrumentRepositoryPort;

public class UserInstrumentService implements CreateUserInstrumentUseCase, UserInstrumentQueryUseCase {
    private final UserInstrumentRepositoryPort repository;

    public UserInstrumentService (UserInstrumentRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public UserInstrument create(UserInstrument userInstrument) {
        return repository.save(userInstrument);
    }

    @Override 
    public Optional<UserInstrument> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<UserInstrument> findAll() {
        return repository.findAll();
    }
}
