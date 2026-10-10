package com.backend.invest.operation.domain;

import java.time.LocalDateTime;

public class Operation {

  private final String id;
  private final String instrumentId;
  private final OperationType type;
  private final Double assetValue;
  private final Double quantity;
  private final Integer annualRate;
  private final LocalDateTime executedAt;
  private final LocalDateTime createdAt;
  private final LocalDateTime updatedAt;

  public Operation(String id, String instrumentId, OperationType type, Double assetValue,
      Double quantity, Integer annualRate, LocalDateTime executedAt, LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    this.id = id;
    this.instrumentId = instrumentId;
    this.type = type;
    this.assetValue = assetValue;
    this.quantity = quantity;
    this.annualRate = annualRate;
    this.executedAt = executedAt;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public String getId() {
    return id;
  }

  public String getInstrumentId() {
    return instrumentId;
  }

  public OperationType getType() {
    return type;
  }

  public Double getAssetValue() {
    return assetValue;
  }

  public Double getQuantity() {
    return quantity;
  }

  public Integer getAnnualRate() {
    return annualRate;
  }

  public LocalDateTime getExecutedAt() {
    return executedAt;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
}
