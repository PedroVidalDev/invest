package com.backend.invest.instrument.domain.service;

import com.backend.invest.instrument.domain.Instrument;
import com.backend.invest.instrument.domain.port.in.CreateInstrumentUseCase;
import com.backend.invest.instrument.domain.port.in.InstrumentQueryUseCase;
import com.backend.invest.instrument.domain.port.out.InstrumentRepositoryPort;

import java.util.List;
import java.util.Optional;

public class InstrumentService implements CreateInstrumentUseCase, InstrumentQueryUseCase {
  private final InstrumentRepositoryPort repository;

  public InstrumentService(InstrumentRepositoryPort repository) {
    this.repository = repository;
  }

  @Override
  public Instrument create(Instrument instrument) {
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
