package com.backend.invest.email.domain.port.in;

import com.backend.invest.email.domain.port.in.command.SendEmailCommand;

public interface SendEmailUseCase {
  void sendEmail(SendEmailCommand command);
}
