package com.backend.invest.instrument.domain.port.in;

import com.backend.invest.instrument.domain.Instrument;
import com.backend.invest.instrument.domain.port.in.command.CreateInstrumentCommand;

public interface CreateInstrumentUseCase {
  Instrument create(CreateInstrumentCommand command);
}
