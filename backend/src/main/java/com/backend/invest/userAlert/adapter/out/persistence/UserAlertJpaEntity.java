package com.backend.invest.userAlert.adapter.out.persistence;

import java.time.LocalDateTime;

import com.backend.invest.userAlert.domain.UserAlert;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "UserAlert")
@Table(name = "tb_user_alerts")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserAlertJpaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String userId;
  private String instrumentId;
  private Double percent;
  private Double value;
  private Boolean active;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static UserAlertJpaEntity from(UserAlert userAlert) {
    return new UserAlertJpaEntity(userAlert.getId(), userAlert.getUserId(),
        userAlert.getInstrumentId(), userAlert.getPercent(), userAlert.getValue(),
        userAlert.getActive(), userAlert.getCreatedAt(), userAlert.getUpdatedAt());
  }

  public UserAlert toDomain() {
    return new UserAlert(id, userId, instrumentId, percent, value, active, createdAt, updatedAt);
  }
}
