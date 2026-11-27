package com.backend.invest.instrument.domain.port.in.command;

import java.time.LocalDateTime;

import com.backend.invest.instrument.domain.IndexerType;
import com.backend.invest.instrument.domain.InstrumentType;

public record CreateInstrumentCommand(InstrumentType type, String symbol, String name,
    IndexerType indexer, LocalDateTime maturityDate) {
}
