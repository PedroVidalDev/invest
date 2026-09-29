package com.backend.invest.operation.domain.service;

import com.backend.invest.operation.domain.Operation;
import com.backend.invest.operation.domain.port.in.CreateOperationUseCase;
import com.backend.invest.operation.domain.port.in.OperationQueryUseCase;
import com.backend.invest.operation.domain.port.out.OperationRepositoryPort;

import java.util.List;
import java.util.Optional;

public class OperationService
        implements CreateOperationUseCase, OperationQueryUseCase {

    private final OperationRepositoryPort repository;

    public OperationService(OperationRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Operation create(Operation operation) {
        return repository.save(operation);
    }

    @Override
    public Optional<Operation> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Operation> findAll() {
        return repository.findAll();
    }
}
