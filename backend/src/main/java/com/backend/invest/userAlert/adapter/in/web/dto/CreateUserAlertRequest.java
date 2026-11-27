package com.backend.invest.userAlert.adapter.in.web.dto;

import com.backend.invest.userAlert.domain.AlertCondition;
import com.backend.invest.userAlert.domain.port.in.command.CreateUserAlertCommand;

public record CreateUserAlertRequest(String userId, String instrumentId, Double percent,
    Double value, AlertCondition condition, Double autoConvertFromValue, String autoConvertTo,
    Boolean active) {
  public CreateUserAlertCommand toCommand() {
    return new CreateUserAlertCommand(userId, instrumentId, percent, value, condition,
        autoConvertFromValue, autoConvertTo, active);
  }
}
