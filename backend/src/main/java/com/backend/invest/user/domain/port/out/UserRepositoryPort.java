package com.backend.invest.user.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.backend.invest.user.domain.User;

public interface UserRepositoryPort {
    User save(User user);

    Optional<User> findById(String id);

    List<User> findAll();
}
