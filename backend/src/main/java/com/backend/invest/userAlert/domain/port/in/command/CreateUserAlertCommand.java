package com.backend.invest.userAlert.domain.port.in.command;

import com.backend.invest.userAlert.domain.AlertCondition;

public record CreateUserAlertCommand(String userId, String instrumentId, Double percent,
    Double value, AlertCondition condition, Double autoConvertFromValue, String autoConvertTo,
    Boolean active) {
}
