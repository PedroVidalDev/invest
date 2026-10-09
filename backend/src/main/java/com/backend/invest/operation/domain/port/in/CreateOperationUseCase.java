package com.backend.invest.operation.domain.port.in;

import com.backend.invest.operation.domain.Operation;

public interface CreateOperationUseCase {
    Operation create(Operation operation);
}

