package com.backend.invest.instrument.adapter.in.web.dto;

import com.backend.invest.instrument.domain.Instrument;
import com.backend.invest.instrument.domain.InstrumentType;

public record InstrumentSummaryResponse(
        String id,
        InstrumentType type,
        String symbol,
        String name
) {

    public static InstrumentSummaryResponse from(Instrument instrument) {
        return new InstrumentSummaryResponse(
                instrument.getId(),
                instrument.getType(),
                instrument.getSymbol(),
                instrument.getName()
        );
    }
}