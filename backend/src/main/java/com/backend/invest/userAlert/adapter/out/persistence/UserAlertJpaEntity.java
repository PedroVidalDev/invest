package com.backend.invest.userAlert.adapter.out.persistence;

import java.time.LocalDateTime;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.backend.invest.userAlert.domain.AlertCondition;
import com.backend.invest.userAlert.domain.UserAlert;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

  @Enumerated(EnumType.STRING)
  @JdbcTypeCode(SqlTypes.NAMED_ENUM)
  private AlertCondition condition;

  private Double autoConvertFromValue;
  private String autoConvertTo;
  private Boolean active;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static UserAlertJpaEntity from(UserAlert userAlert) {
    return new UserAlertJpaEntity(userAlert.getId(), userAlert.getUserId(),
        userAlert.getInstrumentId(), userAlert.getPercent(), userAlert.getValue(),
        userAlert.getCondition(), userAlert.getAutoConvertFromValue(), userAlert.getAutoConvertTo(),
        userAlert.getActive(), userAlert.getCreatedAt(), userAlert.getUpdatedAt());
  }

  public UserAlert toDomain() {
    return new UserAlert(id, userId, instrumentId, percent, value, condition, autoConvertFromValue,
        autoConvertTo, active, createdAt, updatedAt);
  }
}
