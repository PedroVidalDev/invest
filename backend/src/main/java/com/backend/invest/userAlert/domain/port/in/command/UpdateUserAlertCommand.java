package com.backend.invest.userAlert.domain.port.in.command;

import com.backend.invest.userAlert.domain.AlertCondition;

public record UpdateUserAlertCommand(Double percent, Double value, AlertCondition condition,
    Double autoConvertFromValue, String autoConvertTo, Boolean active) {
}
