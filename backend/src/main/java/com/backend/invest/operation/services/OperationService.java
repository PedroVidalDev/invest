package com.backend.invest.operation.services;

import com.backend.invest.operation.repositories.OperationRepository;

public class OperationService {
    private final OperationRepository operationRepository;

    public OperationService(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }
}
