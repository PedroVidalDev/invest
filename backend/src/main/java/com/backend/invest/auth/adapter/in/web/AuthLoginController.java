package com.backend.invest.auth.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.invest.auth.adapter.in.web.dto.LoginRequest;
import com.backend.invest.auth.adapter.in.web.dto.LoginResponse;
import com.backend.invest.auth.domain.port.in.LoginUseCase;

@RestController
@RequestMapping("/auth")
public class AuthLoginController {
  private final LoginUseCase loginUseCase;

  public AuthLoginController(LoginUseCase loginUseCase) {
    this.loginUseCase = loginUseCase;
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
    return ResponseEntity.ok(LoginResponse.from(loginUseCase.login(request.toCommand())));
  }
}
