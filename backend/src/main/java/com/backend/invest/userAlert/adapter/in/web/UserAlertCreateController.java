package com.backend.invest.userAlert.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.invest.userAlert.adapter.in.web.dto.CreateUserAlertRequest;
import com.backend.invest.userAlert.adapter.in.web.dto.UserAlertResponse;
import com.backend.invest.userAlert.domain.port.in.CreateUserAlertUseCase;

@RestController
@RequestMapping("/userAlert")
public class UserAlertCreateController {
  private final CreateUserAlertUseCase createUserAlert;

  public UserAlertCreateController(CreateUserAlertUseCase createUserAlert) {
    this.createUserAlert = createUserAlert;
  }

  @PostMapping
  public ResponseEntity<UserAlertResponse> create(@RequestBody CreateUserAlertRequest request) {
    return ResponseEntity.ok(UserAlertResponse.from(createUserAlert.create(request.toCommand())));
  }
}
