package com.backend.invest.instrument.domain.port.out;

import com.backend.invest.instrument.domain.Instrument;

import java.util.List;
import java.util.Optional;

public interface InstrumentRepositoryPort {
    Instrument save(Instrument instrument);

    Optional<Instrument> findById(String id);

    List<Instrument> findAll();
}