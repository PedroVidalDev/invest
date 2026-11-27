package com.backend.invest.userAlert.adapter.in.web.dto;

import com.backend.invest.userAlert.domain.AlertCondition;
import com.backend.invest.userAlert.domain.port.in.command.UpdateUserAlertCommand;

public record UpdateUserAlertRequest(Double percent, Double value, AlertCondition condition,
    Double autoConvertFromValue, String autoConvertTo, Boolean active) {
  public UpdateUserAlertCommand toCommand() {
    return new UpdateUserAlertCommand(percent, value, condition, autoConvertFromValue,
        autoConvertTo, active);
  }
}
