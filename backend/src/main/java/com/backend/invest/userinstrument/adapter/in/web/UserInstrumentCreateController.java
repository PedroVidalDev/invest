package com.backend.invest.userinstrument.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.invest.userinstrument.adapter.in.web.dto.CreateUserInstrumentRequest;
import com.backend.invest.userinstrument.adapter.in.web.dto.UserInstrumentResponse;
import com.backend.invest.userinstrument.domain.port.in.CreateUserInstrumentUseCase;

@RestController 
@RequestMapping("/userInstrument")
public class UserInstrumentCreateController {
    private final CreateUserInstrumentUseCase createUserInstrument;

    public UserInstrumentCreateController (CreateUserInstrumentUseCase createUserInstrument) {
        this.createUserInstrument = createUserInstrument;
    }

    @PostMapping 
    public ResponseEntity<UserInstrumentResponse> create(
        @RequestBody CreateUserInstrumentRequest request
    ) {
        return ResponseEntity.ok(
            UserInstrumentResponse.from(
                createUserInstrument.create(request.toDomain())
            )
        );
    }
}
