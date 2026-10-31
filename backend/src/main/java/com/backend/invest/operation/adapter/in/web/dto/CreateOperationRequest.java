package com.backend.invest.operation.adapter.in.web.dto;

import com.backend.invest.operation.domain.Operation;
import com.backend.invest.operation.domain.OperationType;

public record CreateOperationRequest(String instrumentId,OperationType type,Double assetValue,Double quantity,Integer annualRate){

public Operation toDomain(){return new Operation(null,instrumentId,type,assetValue,quantity,annualRate,null,null,null);}}
