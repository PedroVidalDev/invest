package com.backend.invest.operation.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationJpaSpringRepository extends JpaRepository<OperationJpaEntity, String> {
}
