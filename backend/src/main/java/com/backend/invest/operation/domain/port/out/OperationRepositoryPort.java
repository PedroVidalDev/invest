package com.backend.invest.operation.domain.port.out;

import com.backend.invest.operation.domain.Operation;

import java.util.List;
import java.util.Optional;

public interface OperationRepositoryPort {
    Operation save(Operation operation);

    Optional<Operation> findById(String id);

    List<Operation> findAll();
}
