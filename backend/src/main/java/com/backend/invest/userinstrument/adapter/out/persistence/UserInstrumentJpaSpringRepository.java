package com.backend.invest.userinstrument.adapter.out.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserInstrumentJpaSpringRepository extends JpaRepository<UserInstrumentJpaEntity, String>{
    List<UserInstrumentJpaEntity> findByUserId(String userId);

    boolean existsByUserIdAndInstrumentId(String userId, String instrumentId);

    @Transactional
    void deleteByUserIdAndInstrumentId(String userId, String instrumentId);
}
