package com.backend.invest.operation.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.invest.operation.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation, String> {
    
}
