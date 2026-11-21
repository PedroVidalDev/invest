package com.backend.invest.instrument.domain.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.backend.invest.instrument.domain.Instrument;
import com.backend.invest.instrument.domain.port.in.CreateInstrumentUseCase;
import com.backend.invest.instrument.domain.port.in.InstrumentQueryUseCase;
import com.backend.invest.instrument.domain.port.in.command.CreateInstrumentCommand;
import com.backend.invest.instrument.domain.port.out.InstrumentRepositoryPort;

public class InstrumentService implements CreateInstrumentUseCase, InstrumentQueryUseCase {
  private final InstrumentRepositoryPort repository;

  public InstrumentService(InstrumentRepositoryPort repository) {
    this.repository = repository;
  }

  @Override
  public Instrument create(CreateInstrumentCommand command) {
    Instrument instrument = new Instrument(null, command.type(), command.symbol(), command.name(),
        command.indexer(), command.maturityDate(), LocalDateTime.now(), LocalDateTime.now());

    return repository.save(instrument);
  }

  @Override
  public Optional<Instrument> findById(String id) {
    return repository.findById(id);
  }

  @Override
  public List<Instrument> findAll() {
    return repository.findAll();
  }
}
