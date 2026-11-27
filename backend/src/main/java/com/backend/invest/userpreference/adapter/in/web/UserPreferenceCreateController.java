package com.backend.invest.userpreference.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.invest.userpreference.adapter.in.web.dto.CreateUserPreferenceRequest;
import com.backend.invest.userpreference.adapter.in.web.dto.UserPreferenceResponse;
import com.backend.invest.userpreference.domain.port.in.CreateUserPreferenceUseCase;
import com.backend.invest.userpreference.domain.port.in.command.CreateUserPreferenceCommand;

@RestController
@RequestMapping("/userpreference")
public class UserPreferenceCreateController {

  private final CreateUserPreferenceUseCase createUserPreference;

  public UserPreferenceCreateController(CreateUserPreferenceUseCase createUserPreference) {
    this.createUserPreference = createUserPreference;
  }

  @PostMapping
  public ResponseEntity<UserPreferenceResponse> create(
      @RequestBody CreateUserPreferenceRequest request, @AuthenticationPrincipal String userId) {
    return ResponseEntity.ok(UserPreferenceResponse
        .from(createUserPreference.create(new CreateUserPreferenceCommand(userId,
            request.displayCurrency(), request.language(), request.theme()))));
  }
}
