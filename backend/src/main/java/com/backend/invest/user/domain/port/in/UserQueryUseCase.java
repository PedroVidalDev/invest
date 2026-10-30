package com.backend.invest.user.domain.port.in;

import java.util.List;

import com.backend.invest.user.domain.User;

public interface UserQueryUseCase {
    User findById(String id);

    List<User> findAll();
}
