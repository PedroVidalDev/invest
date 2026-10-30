package com.backend.invest.userinstrument.domain.port.out;

import java.util.List;
import com.backend.invest.userinstrument.domain.UserInstrument;

public interface UserInstrumentRepositoryPort {
    UserInstrument save(UserInstrument userInstrument);

    List<UserInstrument> findByUserId(String userId);

    boolean existsByUserIdAndInstrumentId(String userId, String instrumentId);

    void deleteByUserIdAndInstrumentId(String userId, String instrumentId);
}
