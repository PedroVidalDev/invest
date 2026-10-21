package com.backend.invest.userinstrument.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.backend.invest.userinstrument.domain.UserInstrument;

public interface UserInstrumentRepositoryPort {
    UserInstrument save(UserInstrument userInstrument);

    Optional<UserInstrument> findById(String Id);

    List<UserInstrument> findAll();
}
