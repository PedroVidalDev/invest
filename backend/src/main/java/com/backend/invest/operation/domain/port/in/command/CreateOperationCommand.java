package com.backend.invest.operation.domain.port.in.command;

import com.backend.invest.operation.domain.OperationType;

public record CreateOperationCommand(String instrumentId, OperationType type, Double assetValue,
    Double quantity, Integer annualRate) {
}
