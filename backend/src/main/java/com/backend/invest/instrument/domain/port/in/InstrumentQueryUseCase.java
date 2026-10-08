package com.backend.invest.instrument.domain.port.in;

import com.backend.invest.instrument.domain.Instrument;

import java.util.List;
import java.util.Optional;

public interface InstrumentQueryUseCase {
    Optional<Instrument> findById(String id);

    List<Instrument> findAll();
}