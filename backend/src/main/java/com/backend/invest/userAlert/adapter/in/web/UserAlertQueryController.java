package com.backend.invest.userAlert.adapter.in.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.invest.userAlert.adapter.in.web.dto.UserAlertResponse;
import com.backend.invest.userAlert.adapter.in.web.dto.UserAlertSummaryResponse;
import com.backend.invest.userAlert.domain.port.in.UserAlertQueryUseCase;

@RestController
@RequestMapping("/userAlert")
public class UserAlertQueryController {
  private final UserAlertQueryUseCase userAlertQuery;

  public UserAlertQueryController(UserAlertQueryUseCase userAlertQuery) {
    this.userAlertQuery = userAlertQuery;
  }

  @GetMapping("/{userId}/{id}")
  public ResponseEntity<UserAlertResponse> get(@PathVariable String userId,
      @PathVariable String id) {
    return ResponseEntity.ok(UserAlertResponse.from(userAlertQuery.findByIdAndUserId(id, userId)));
  }

  @GetMapping("/{userId}")
  public ResponseEntity<List<UserAlertSummaryResponse>> list(@PathVariable String userId) {
    List<UserAlertSummaryResponse> body =
        userAlertQuery.findByUserId(userId).stream().map(UserAlertSummaryResponse::from).toList();

    return ResponseEntity.ok(body);
  }
}
