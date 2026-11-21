package com.backend.invest.userAlert.domain.port.in;

import com.backend.invest.userAlert.domain.UserAlert;
import com.backend.invest.userAlert.domain.port.in.command.UpdateUserAlertCommand;

public interface UpdateUserAlertUseCase {
  UserAlert update(String id, UpdateUserAlertCommand command);
}
