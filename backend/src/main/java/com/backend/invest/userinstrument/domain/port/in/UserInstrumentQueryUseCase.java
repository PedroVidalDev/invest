package com.backend.invest.userinstrument.domain.port.in;

import java.util.List;

import com.backend.invest.userinstrument.domain.UserInstrument;

public interface UserInstrumentQueryUseCase {
    List<UserInstrument> findByUserId(String userId);
}
