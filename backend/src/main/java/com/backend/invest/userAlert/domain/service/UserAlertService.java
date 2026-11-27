package com.backend.invest.userAlert.domain.service;

import java.time.LocalDateTime;
import java.util.List;

import com.backend.invest.user.domain.port.in.UserQueryUseCase;
import com.backend.invest.userAlert.domain.UserAlert;
import com.backend.invest.userAlert.domain.exception.UserAlertAlreadyExistsException;
import com.backend.invest.userAlert.domain.exception.UserAlertNotFoundException;
import com.backend.invest.userAlert.domain.port.in.CreateUserAlertUseCase;
import com.backend.invest.userAlert.domain.port.in.DeleteUserAlertUseCase;
import com.backend.invest.userAlert.domain.port.in.UpdateUserAlertUseCase;
import com.backend.invest.userAlert.domain.port.in.UserAlertQueryUseCase;
import com.backend.invest.userAlert.domain.port.in.command.CreateUserAlertCommand;
import com.backend.invest.userAlert.domain.port.in.command.UpdateUserAlertCommand;
import com.backend.invest.userAlert.domain.port.out.UserAlertRepositoryPort;

public class UserAlertService implements CreateUserAlertUseCase, UserAlertQueryUseCase,
    UpdateUserAlertUseCase, DeleteUserAlertUseCase {
  private final UserAlertRepositoryPort repository;
  private final UserQueryUseCase userQuery;

  public UserAlertService(UserAlertRepositoryPort repository, UserQueryUseCase userQuery) {
    this.repository = repository;
    this.userQuery = userQuery;
  }

  @Override
  public UserAlert create(CreateUserAlertCommand command) {
    userQuery.findById(command.userId());

    if (repository.existsByUserIdAndInstrumentId(command.userId(), command.instrumentId())) {
      throw new UserAlertAlreadyExistsException(command.userId(), command.instrumentId());
    }

    UserAlert userAlert = new UserAlert(null, command.userId(), command.instrumentId(),
        command.percent(), command.value(), command.condition(), command.autoConvertFromValue(),
        command.autoConvertTo(), command.active() != null ? command.active() : true,
        LocalDateTime.now(), LocalDateTime.now());

    return repository.save(userAlert);
  }

  @Override
  public UserAlert findByIdAndUserId(String id, String userId) {
    userQuery.findById(userId);

    return repository.findByIdAndUserId(id, userId)
        .orElseThrow(() -> new UserAlertNotFoundException(id));
  }

  @Override
  public List<UserAlert> findByUserId(String userId) {
    userQuery.findById(userId);

    return repository.findByUserId(userId);
  }

  @Override
  public UserAlert update(String id, UpdateUserAlertCommand command) {
    UserAlert userAlert =
        repository.findById(id).orElseThrow(() -> new UserAlertNotFoundException(id));

    UserAlert updatedUserAlert = new UserAlert(userAlert.getId(), userAlert.getUserId(),
        userAlert.getInstrumentId(), command.percent(), command.value(), command.condition(),
        command.autoConvertFromValue(), command.autoConvertTo(),
        command.active() != null ? command.active() : userAlert.getActive(),
        userAlert.getCreatedAt(), LocalDateTime.now());

    return repository.save(updatedUserAlert);
  }

  @Override
  public void delete(String userId, String instrumentId) {
    if (!repository.existsByUserIdAndInstrumentId(userId, instrumentId)) {
      throw new UserAlertNotFoundException(userId, instrumentId);
    }

    repository.deleteByUserIdAndInstrumentId(userId, instrumentId);
  }
}
