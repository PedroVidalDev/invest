package com.backend.invest.userAlert.domain.port.out;

import com.backend.invest.instrument.domain.Instrument;
import com.backend.invest.userAlert.domain.UserAlert;

public interface AlertActionPort {
  void execute(UserAlert alert, Instrument instrument, Double currentPrice);
}
