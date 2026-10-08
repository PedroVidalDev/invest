package com.backend.invest.instrument.domain;

import java.time.LocalDateTime;

public class Instrument {

    private String id;
    private InstrumentType type;
    private String symbol;
    private String name;
    private IndexerType indexer;
    private LocalDateTime maturityDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Instrument(String id, InstrumentType type, String symbol, String name, IndexerType indexer, LocalDateTime maturityDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.type = type;
        this.symbol = symbol;
        this.name = name;
        this.indexer = indexer;
        this.maturityDate = maturityDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public InstrumentType getType() {
        return type;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public IndexerType getIndexer() {
        return indexer;
    }

    public LocalDateTime getMaturityDate() {
        return maturityDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}