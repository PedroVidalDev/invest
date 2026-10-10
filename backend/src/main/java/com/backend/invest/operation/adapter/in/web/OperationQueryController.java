package com.backend.invest.operation.adapter.in.web;

import com.backend.invest.operation.adapter.in.web.dto.OperationResponse;
import com.backend.invest.operation.adapter.in.web.dto.OperationSummaryResponse;
import com.backend.invest.operation.domain.port.in.OperationQueryUseCase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/operations")
public class OperationQueryController {

  private final OperationQueryUseCase operationQuery;

  public OperationQueryController(OperationQueryUseCase operationQuery) {
    this.operationQuery = operationQuery;
  }

  @GetMapping("/{id}")
  public ResponseEntity<OperationResponse> get(@PathVariable String id) {
    return operationQuery.findById(id).map(OperationResponse::from).map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping
  public ResponseEntity<List<OperationSummaryResponse>> list() {
    List<OperationSummaryResponse> body =
        operationQuery.findAll().stream().map(OperationSummaryResponse::from).toList();
    return ResponseEntity.ok(body);
  }
}
