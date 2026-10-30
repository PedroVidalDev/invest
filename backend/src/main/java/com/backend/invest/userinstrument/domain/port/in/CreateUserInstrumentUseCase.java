package com.backend.invest.userinstrument.domain.port.in;

import com.backend.invest.userinstrument.domain.UserInstrument;
import com.backend.invest.userinstrument.domain.port.in.command.CreateUserInstrumentCommand;

public interface CreateUserInstrumentUseCase {
    UserInstrument create(CreateUserInstrumentCommand command);
}
