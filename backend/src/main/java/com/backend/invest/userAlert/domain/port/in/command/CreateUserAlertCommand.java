package com.backend.invest.userAlert.domain.port.in.command;

public record CreateUserAlertCommand(String userId, String instrumentId, Double percent,
    Double value, Double autoConvertFromValue, String autoConvertTo, Boolean active) {
}
