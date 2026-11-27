package com.backend.invest.operation.domain.port.in;

import com.backend.invest.operation.domain.Operation;
import com.backend.invest.operation.domain.port.in.command.CreateOperationCommand;

public interface CreateOperationUseCase {
  Operation create(String userId, CreateOperationCommand command);
}

