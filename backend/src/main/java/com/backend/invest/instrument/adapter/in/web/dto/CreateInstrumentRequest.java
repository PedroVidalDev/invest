package com.backend.invest.instrument.adapter.in.web.dto;

import java.time.LocalDateTime;

import com.backend.invest.instrument.domain.IndexerType;
import com.backend.invest.instrument.domain.InstrumentType;
import com.backend.invest.instrument.domain.port.in.command.CreateInstrumentCommand;

public record CreateInstrumentRequest(InstrumentType type, String symbol, String name,
    IndexerType indexer, LocalDateTime maturityDate) {

  public CreateInstrumentCommand toCommand() {
    return new CreateInstrumentCommand(type, symbol, name, indexer, maturityDate);
  }
}
