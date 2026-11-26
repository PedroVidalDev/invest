package com.backend.invest.userAlert.domain.port.in.command;

public record UpdateUserAlertCommand(Double percent, Double value, Double autoConvertFromValue,
    String autoConvertTo, Boolean active) {
}
