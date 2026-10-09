package com.backend.invest.operation.domain.port.in;

import com.backend.invest.operation.domain.Operation;

import java.util.List;
import java.util.Optional;

public interface OperationQueryUseCase {
    Optional<Operation> findById(String id);

    List<Operation> findAll();
}
