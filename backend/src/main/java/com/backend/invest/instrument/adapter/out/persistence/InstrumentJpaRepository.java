package com.backend.invest.instrument.adapter.out.persistence;

import com.backend.invest.instrument.domain.Instrument;
import com.backend.invest.instrument.domain.port.out.InstrumentRepositoryPort;

import java.util.List;
import java.util.Optional;

public class InstrumentJpaRepository implements InstrumentRepositoryPort {
    private final InstrumentJpaSpringRepository springRepository;

    public InstrumentJpaRepository(InstrumentJpaSpringRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public Instrument save(Instrument instrument) {
        return springRepository.save(InstrumentJpaEntity.from(instrument)).toDomain();
    }

    @Override
    public Optional<Instrument> findById(String id) {
        return springRepository.findById(id).map(InstrumentJpaEntity::toDomain);
    }

    @Override
    public List<Instrument> findAll() {
        return springRepository.findAll().stream().map(InstrumentJpaEntity::toDomain).toList();
    }
}
