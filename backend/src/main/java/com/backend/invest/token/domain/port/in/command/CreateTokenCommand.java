package com.backend.invest.token.domain.port.in.command;

import com.backend.invest.token.domain.ServiceType;

public record CreateTokenCommand(
  String userId,
  ServiceType service
) {}
