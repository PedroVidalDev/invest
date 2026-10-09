package com.backend.invest.operation.adapter.in.web;

import com.backend.invest.operation.adapter.in.web.dto.CreateOperationRequest;
import com.backend.invest.operation.adapter.in.web.dto.OperationResponse;
import com.backend.invest.operation.domain.port.in.CreateOperationUseCase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operations")
public class OperationCreateController {

    private final CreateOperationUseCase createOperation;

    public OperationCreateController(CreateOperationUseCase createOperation) {
        this.createOperation = createOperation;
    }

    @PostMapping
    public ResponseEntity<OperationResponse> create(@RequestBody CreateOperationRequest request) {
        return ResponseEntity.ok(
                OperationResponse.from(createOperation.create(request.toDomain())));
    }
}
