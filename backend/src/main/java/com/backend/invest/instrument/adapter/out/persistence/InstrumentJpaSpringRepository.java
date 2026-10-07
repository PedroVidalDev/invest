package com.backend.invest.instrument.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrumentJpaSpringRepository extends JpaRepository<InstrumentJpaEntity, String> {
}
