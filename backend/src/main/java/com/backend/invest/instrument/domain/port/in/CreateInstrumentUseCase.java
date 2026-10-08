package com.backend.invest.instrument.domain.port.in;

import com.backend.invest.instrument.domain.Instrument;

public interface CreateInstrumentUseCase {
    Instrument create(Instrument instrument);
}