package com.backend.invest.userAlert.domain;

import java.time.LocalDateTime;

public class UserAlert {
  private String id;
  private String userId;
  private String instrumentId;
  private Double percent;
  private Double value;
  private Double autoConvertFromValue;
  private String autoConvertTo;
  private Boolean active;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public UserAlert(String id, String userId, String instrumentId, Double percent, Double value,
      Double autoConvertFromValue, String autoConvertTo, Boolean active, LocalDateTime createdAt,
      LocalDateTime updatedAt) {
    this.id = id;
    this.userId = userId;
    this.instrumentId = instrumentId;
    this.percent = percent;
    this.value = value;
    this.autoConvertFromValue = autoConvertFromValue;
    this.autoConvertTo = autoConvertTo;
    this.active = active;
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

  public Double getPercent() {
    return this.percent;
  }

  public Double getValue() {
    return this.value;
  }

  public Double getAutoConvertFromValue() {
    return this.autoConvertFromValue;
  }

  public String getAutoConvertTo() {
    return this.autoConvertTo;
  }

  public Boolean getActive() {
    return this.active;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return this.updatedAt;
  }
}
