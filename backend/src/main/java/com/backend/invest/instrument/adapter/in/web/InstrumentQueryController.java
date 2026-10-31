package com.backend.invest.instrument.adapter.in.web;

import com.backend.invest.instrument.adapter.in.web.dto.InstrumentResponse;
import com.backend.invest.instrument.adapter.in.web.dto.InstrumentSummaryResponse;
import com.backend.invest.instrument.domain.port.in.InstrumentQueryUseCase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/instruments")
public class InstrumentQueryController {

  private final InstrumentQueryUseCase instrumentQuery;

  public InstrumentQueryController(InstrumentQueryUseCase instrumentQuery) {
    this.instrumentQuery = instrumentQuery;
  }

  @GetMapping("/{id}")
  public ResponseEntity<InstrumentResponse> get(@PathVariable String id) {
    return instrumentQuery.findById(id).map(InstrumentResponse::from).map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<List<InstrumentSummaryResponse>> list() {
    List<InstrumentSummaryResponse> body =
        instrumentQuery.findAll().stream().map(InstrumentSummaryResponse::from).toList();

    return ResponseEntity.ok(body);
  }
}
