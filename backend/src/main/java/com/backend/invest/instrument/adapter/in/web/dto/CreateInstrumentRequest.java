package com.backend.invest.instrument.adapter.in.web.dto;

import com.backend.invest.instrument.domain.IndexerType;
import com.backend.invest.instrument.domain.Instrument;
import com.backend.invest.instrument.domain.InstrumentType;

import java.time.LocalDateTime;

public record CreateInstrumentRequest (
        InstrumentType type,
        String symbol,
        String name,
        IndexerType indexer,
        LocalDateTime maturityDate
    ){

    public Instrument toDomain() {
        return new Instrument (
                null,
                type,
                symbol,
                name,
                indexer,
                maturityDate,
                null,
                null
        );
    }
}
