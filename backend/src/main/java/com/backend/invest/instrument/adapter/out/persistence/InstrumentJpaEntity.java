package com.backend.invest.instrument.adapter.out.persistence;

import com.backend.invest.instrument.domain.IndexerType;
import com.backend.invest.instrument.domain.Instrument;
import com.backend.invest.instrument.domain.InstrumentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Instrument")
@Table(name = "tb_instruments")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InstrumentJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private InstrumentType type;

    private String symbol;
    private String name;

    private IndexerType indexer;

    private LocalDateTime maturityDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static InstrumentJpaEntity from(Instrument instrument) {
        return new InstrumentJpaEntity(
                instrument.getId(),
                instrument.getType(),
                instrument.getSymbol(),
                instrument.getName(),
                instrument.getIndexer(),
                instrument.getMaturityDate(),
                instrument.getCreatedAt(),
                instrument.getUpdatedAt()
        );
    }

    public Instrument toDomain() {
        return new Instrument(
                id,
                type,
                symbol,
                name,
                indexer,
                maturityDate,
                createdAt,
                updatedAt
        );
    }
}
