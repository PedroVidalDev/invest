package com.backend.invest.operation.entities;

import java.time.LocalDateTime;

import com.backend.invest.operation.enums.OperationType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity (name = "Operation")
@Table (name = "tb_operations")
@Getter 
@AllArgsConstructor 
@NoArgsConstructor 
public class Operation {
    @Id @GeneratedValue (strategy = jakarta.persistence.GenerationType.UUID)
    private String id;

    private String instrumentId;

    private OperationType type;

    private Double assetValue;
    private Double quantity;

    private Integer annualRate;

    private LocalDateTime executedAt;    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
