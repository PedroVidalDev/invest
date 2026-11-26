package com.backend.invest.email.domain.port.in.command;

import com.backend.invest.email.domain.EmailType;

public record SendEmailCommand(
  EmailType type,
  String code,
  String to,
  String name
){}
