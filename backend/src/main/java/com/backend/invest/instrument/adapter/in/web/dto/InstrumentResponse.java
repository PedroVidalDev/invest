package com.backend.invest.instrument.adapter.in.web.dto;

import com.backend.invest.instrument.domain.IndexerType;
import com.backend.invest.instrument.domain.Instrument;
import com.backend.invest.instrument.domain.InstrumentType;

import java.time.LocalDateTime;

public record InstrumentResponse(String id,InstrumentType type,String symbol,String name,IndexerType indexer,LocalDateTime maturityDate){

public static InstrumentResponse from(Instrument instrument){return new InstrumentResponse(instrument.getId(),instrument.getType(),instrument.getSymbol(),instrument.getName(),instrument.getIndexer(),instrument.getMaturityDate());}}
