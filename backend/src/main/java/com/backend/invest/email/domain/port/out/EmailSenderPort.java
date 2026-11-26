package com.backend.invest.email.domain.port.out;

public interface EmailSenderPort {
  void send(String to, String subject, String body);
}