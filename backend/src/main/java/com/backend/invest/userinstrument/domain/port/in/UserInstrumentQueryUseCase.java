package com.backend.invest.userinstrument.domain.port.in;

import java.util.List;
import java.util.Optional;

import com.backend.invest.userinstrument.domain.UserInstrument;

public interface UserInstrumentQueryUseCase {
    Optional<UserInstrument> findById(String id);

    List<UserInstrument> findAll();
}
