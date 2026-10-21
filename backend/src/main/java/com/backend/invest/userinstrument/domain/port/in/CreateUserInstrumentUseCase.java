package com.backend.invest.userinstrument.domain.port.in;

import com.backend.invest.userinstrument.domain.UserInstrument;

public interface CreateUserInstrumentUseCase {
    UserInstrument create(UserInstrument UserInstrument);
}
