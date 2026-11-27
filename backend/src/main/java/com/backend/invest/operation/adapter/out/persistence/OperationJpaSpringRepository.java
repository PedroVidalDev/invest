package com.backend.invest.operation.adapter.out.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationJpaSpringRepository extends JpaRepository<OperationJpaEntity, String> {
  List<OperationJpaEntity> findByUserId(String userId);
}
