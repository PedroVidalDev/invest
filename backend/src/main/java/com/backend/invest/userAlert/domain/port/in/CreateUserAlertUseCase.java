package com.backend.invest.userAlert.domain.port.in;

import com.backend.invest.userAlert.domain.UserAlert;
import com.backend.invest.userAlert.domain.port.in.command.CreateUserAlertCommand;

public interface CreateUserAlertUseCase {
  UserAlert create(CreateUserAlertCommand command);
}
