package com.backend.invest.operation.adapter.out.persistence;

import com.backend.invest.operation.domain.Operation;
import com.backend.invest.operation.domain.OperationType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Operation")
@Table(name = "tb_operations")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OperationJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String instrumentId;

    private OperationType type;

    private Double assetValue;
    private Double quantity;

    private Integer annualRate;

    private LocalDateTime executedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static OperationJpaEntity from(Operation operation) {
        return new OperationJpaEntity(
                operation.getId(),
                operation.getInstrumentId(),
                operation.getType(),
                operation.getAssetValue(),
                operation.getQuantity(),
                operation.getAnnualRate(),
                operation.getExecutedAt(),
                operation.getCreatedAt(),
                operation.getUpdatedAt());
    }

    public Operation toDomain() {
        return new Operation(
                id,
                instrumentId,
                type,
                assetValue,
                quantity,
                annualRate,
                executedAt,
                createdAt,
                updatedAt);
    }
}
