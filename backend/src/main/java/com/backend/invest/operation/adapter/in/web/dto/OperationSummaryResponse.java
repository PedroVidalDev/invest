package com.backend.invest.operation.adapter.in.web.dto;

import com.backend.invest.operation.domain.Operation;
import com.backend.invest.operation.domain.OperationType;

public record OperationSummaryResponse(
        String id,
        OperationType type,
        Double assetValue) {

    public static OperationSummaryResponse from(Operation operation) {
        return new OperationSummaryResponse(
                operation.getId(),
                operation.getType(),
                operation.getAssetValue());
    }
}
