package com.backend.invest.userAlert.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.invest.userAlert.domain.port.in.DeleteUserAlertUseCase;

@RestController
@RequestMapping("/userAlert")
public class UserAlertDeleteController {
  private final DeleteUserAlertUseCase deleteUserAlert;

  public UserAlertDeleteController(DeleteUserAlertUseCase deleteUserAlert) {
    this.deleteUserAlert = deleteUserAlert;
  }

  @DeleteMapping("/{userId}/{instrumentId}")
  public ResponseEntity<Void> delete(@PathVariable String userId,
      @PathVariable String instrumentId) {
    deleteUserAlert.delete(userId, instrumentId);

    return ResponseEntity.noContent().build();
  }
}
