package com.backend.invest.instrument.adapter.in.web;

import com.backend.invest.instrument.adapter.in.web.dto.CreateInstrumentRequest;
import com.backend.invest.instrument.adapter.in.web.dto.InstrumentResponse;
import com.backend.invest.instrument.domain.port.in.CreateInstrumentUseCase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instruments")
public class InstrumentCreateController {

  private final CreateInstrumentUseCase createInstrument;

  public InstrumentCreateController(CreateInstrumentUseCase createInstrument) {
    this.createInstrument = createInstrument;
  }

  @PostMapping
  public ResponseEntity<InstrumentResponse> create(@RequestBody CreateInstrumentRequest request) {
    return ResponseEntity.ok(InstrumentResponse.from(createInstrument.create(request.toDomain())));
  }
}
