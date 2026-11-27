package com.backend.invest.operation.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.backend.invest.operation.domain.Operation;
import com.backend.invest.operation.domain.port.in.CreateOperationUseCase;
import com.backend.invest.operation.domain.port.in.OperationQueryUseCase;
import com.backend.invest.operation.domain.port.in.command.CreateOperationCommand;
import com.backend.invest.operation.domain.port.out.OperationRepositoryPort;

public class OperationService implements CreateOperationUseCase, OperationQueryUseCase {

  private final OperationRepositoryPort repository;

  public OperationService(OperationRepositoryPort repository) {
    this.repository = repository;
  }

  @Override
  public Operation create(String userId, CreateOperationCommand command) {
    Operation operation = new Operation(null, userId, command.instrumentId(), command.type(),
        command.assetValue(), command.quantity(), command.annualRate(), null, LocalDateTime.now(),
        LocalDateTime.now());

    return repository.save(operation);
  }

  @Override
  public Optional<Operation> findById(String id) {
    return repository.findById(id);
  }

  @Override
  public List<Operation> findByUserId(String userId) {
    return repository.findByUserId(userId);
  }
}
