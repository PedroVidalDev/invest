package com.backend.invest.operation.adapter.in.web.dto;

import com.backend.invest.operation.domain.Operation;
import com.backend.invest.operation.domain.OperationType;

import java.time.LocalDateTime;

public record OperationResponse(String id,String instrumentId,OperationType type,Double assetValue,Double quantity,Integer annualRate,LocalDateTime executedAt){

public static OperationResponse from(Operation operation){return new OperationResponse(operation.getId(),operation.getInstrumentId(),operation.getType(),operation.getAssetValue(),operation.getQuantity(),operation.getAnnualRate(),operation.getExecutedAt());}}
