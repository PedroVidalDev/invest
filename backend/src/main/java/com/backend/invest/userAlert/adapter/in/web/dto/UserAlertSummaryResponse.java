package com.backend.invest.userAlert.adapter.in.web.dto;

import com.backend.invest.userAlert.domain.UserAlert;

public record UserAlertSummaryResponse(String id, String userId, String instrumentId,
    Double percent, Double value, Double autoConvertFromValue, String autoConvertTo,
    Boolean active) {
  public static UserAlertSummaryResponse from(UserAlert userAlert) {
    return new UserAlertSummaryResponse(userAlert.getId(), userAlert.getUserId(),
        userAlert.getInstrumentId(), userAlert.getPercent(), userAlert.getValue(),
        userAlert.getAutoConvertFromValue(), userAlert.getAutoConvertTo(), userAlert.getActive());
  }
}
