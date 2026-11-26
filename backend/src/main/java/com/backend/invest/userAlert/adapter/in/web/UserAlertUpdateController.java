package com.backend.invest.userAlert.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.invest.userAlert.adapter.in.web.dto.UpdateUserAlertRequest;
import com.backend.invest.userAlert.adapter.in.web.dto.UserAlertResponse;
import com.backend.invest.userAlert.domain.port.in.UpdateUserAlertUseCase;

@RestController
@RequestMapping("/userAlert")
public class UserAlertUpdateController {
  private final UpdateUserAlertUseCase updateUserAlert;

  public UserAlertUpdateController(UpdateUserAlertUseCase updateUserAlert) {
    this.updateUserAlert = updateUserAlert;
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserAlertResponse> update(@PathVariable String id,
      @RequestBody UpdateUserAlertRequest request) {
    return ResponseEntity
        .ok(UserAlertResponse.from(updateUserAlert.update(id, request.toCommand())));
  }
}
