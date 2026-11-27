package com.backend.invest.userinstrument.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.invest.userinstrument.adapter.in.web.dto.CreateUserInstrumentRequest;
import com.backend.invest.userinstrument.adapter.in.web.dto.UserInstrumentResponse;
import com.backend.invest.userinstrument.domain.port.in.CreateUserInstrumentUseCase;
import com.backend.invest.userinstrument.domain.port.in.command.CreateUserInstrumentCommand;

@RestController
@RequestMapping("/userInstrument")
public class UserInstrumentCreateController {
  private final CreateUserInstrumentUseCase createUserInstrument;

  public UserInstrumentCreateController(CreateUserInstrumentUseCase createUserInstrument) {
    this.createUserInstrument = createUserInstrument;
  }

  @PostMapping
  public ResponseEntity<UserInstrumentResponse> create(
      @RequestBody CreateUserInstrumentRequest request, @AuthenticationPrincipal String userId) {

    return ResponseEntity.ok(UserInstrumentResponse.from(createUserInstrument
        .create(new CreateUserInstrumentCommand(userId, request.instrumentId()))));
  }
}
