package com.backend.invest.userAlert.adapter.in.web.dto;

import com.backend.invest.userAlert.domain.port.in.command.UpdateUserAlertCommand;

public record UpdateUserAlertRequest(Double percent, Double value, Boolean active) {
  public UpdateUserAlertCommand toCommand() {
    return new UpdateUserAlertCommand(percent, value, active);
  }
}
