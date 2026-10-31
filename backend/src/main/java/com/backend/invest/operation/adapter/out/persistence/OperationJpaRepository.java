package com.backend.invest.operation.adapter.out.persistence;

import com.backend.invest.operation.domain.Operation;
import com.backend.invest.operation.domain.port.out.OperationRepositoryPort;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OperationJpaRepository implements OperationRepositoryPort {

  private final OperationJpaSpringRepository springRepository;

  public OperationJpaRepository(OperationJpaSpringRepository springRepository) {
    this.springRepository = springRepository;
  }

  @Override
  public Operation save(Operation operation) {
    return springRepository.save(OperationJpaEntity.from(operation)).toDomain();
  }

  @Override
  public Optional<Operation> findById(String id) {
    return springRepository.findById(id).map(OperationJpaEntity::toDomain);
  }

  @Override
  public List<Operation> findAll() {
    return springRepository.findAll().stream().map(OperationJpaEntity::toDomain).toList();
  }
}
