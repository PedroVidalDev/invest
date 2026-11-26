package com.backend.invest.userAlert.domain.port.in.command;

public record UpdateUserAlertCommand(Double percent, Double value, Boolean active) {
}
