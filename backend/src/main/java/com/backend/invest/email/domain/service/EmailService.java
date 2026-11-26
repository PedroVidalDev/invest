package com.backend.invest.email.domain.service;

import com.backend.invest.email.domain.Email;
import com.backend.invest.email.domain.port.in.SendEmailUseCase;
import com.backend.invest.email.domain.port.in.command.SendEmailCommand;
import com.backend.invest.email.domain.port.out.EmailSenderPort;

public class EmailService implements SendEmailUseCase {
  private final EmailSenderPort sender;

  public EmailService(EmailSenderPort sender) {
    this.sender = sender;
  }

  @Override
  public void sendEmail(SendEmailCommand command) {
    Email email = new Email(command.type(), command.code(), command.to(), command.name());

    sender.send(email.getTo(), subject(email), body(email));
  }

  private String subject(Email email) {
    return switch (email.getType()) {
      case VERIFY -> "Verifique seu email";
      case RECOVERY -> "Recuperação de senha";
    };
  }

  private String body(Email email) {
    String greeting = email.getName() == null || email.getName().isBlank()
        ? "Olá"
        : "Olá, " + email.getName();

    return switch (email.getType()) {
      case VERIFY -> greeting + ",\n\nSeu código de verificação é: " + email.getCode();
      case RECOVERY -> greeting + ",\n\nSeu código de recuperação é: " + email.getCode();
    };
  }
}
