package com.backend.invest.userinstrument.domain;

import java.time.LocalDateTime;

public class UserInstrument {
    private String id;
    private String userId;
    private String instrumentId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserInstrument (String id, String userId, String instrumentId, LocalDateTime createdAt, LocalDateTime updatedAt ){
        this.id = id;
        this.userId = userId;
        this.instrumentId = instrumentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return this.id;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getInstrumentId() {
        return this.instrumentId;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }
}
