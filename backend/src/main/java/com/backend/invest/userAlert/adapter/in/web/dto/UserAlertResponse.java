package com.backend.invest.userAlert.adapter.in.web.dto;

import com.backend.invest.userAlert.domain.AlertCondition;
import com.backend.invest.userAlert.domain.UserAlert;

public record UserAlertResponse(String id, String userId, String instrumentId, Double percent,
    Double value, AlertCondition condition, Double autoConvertFromValue, String autoConvertTo,
    Boolean active) {
  public static UserAlertResponse from(UserAlert userAlert) {
    return new UserAlertResponse(userAlert.getId(), userAlert.getUserId(),
        userAlert.getInstrumentId(), userAlert.getPercent(), userAlert.getValue(),
        userAlert.getCondition(), userAlert.getAutoConvertFromValue(), userAlert.getAutoConvertTo(),
        userAlert.getActive());
  }
}
