package com.backend.invest.user.domain.port.in;

import java.util.List;
import java.util.Optional;

import com.backend.invest.user.domain.User;

public interface UserQueryUseCase {
    Optional<User> findById(String id);

    List<User> findAll();
}
