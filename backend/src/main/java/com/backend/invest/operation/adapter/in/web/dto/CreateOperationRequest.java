package com.backend.invest.operation.adapter.in.web.dto;

import com.backend.invest.operation.domain.OperationType;
import com.backend.invest.operation.domain.port.in.command.CreateOperationCommand;

public record CreateOperationRequest(String instrumentId, OperationType type, Double assetValue,
    Double quantity, Integer annualRate) {

  public CreateOperationCommand toCommand() {
    return new CreateOperationCommand(instrumentId, type, assetValue, quantity, annualRate);
  }
}
