package com.backend.invest.token.domain.port.in;

import com.backend.invest.token.domain.Token;
import com.backend.invest.token.domain.port.in.command.CreateTokenCommand;

public interface CreateTokenUseCase {
  Token create (CreateTokenCommand command);
}
