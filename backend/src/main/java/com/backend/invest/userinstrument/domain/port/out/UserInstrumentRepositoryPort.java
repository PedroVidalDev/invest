package com.backend.invest.userinstrument.domain.port.out;

import java.util.List;
import com.backend.invest.userinstrument.domain.UserInstrument;

public interface UserInstrumentRepositoryPort {
    UserInstrument save(UserInstrument userInstrument);

    List<UserInstrument> findByUserId(String userId);

    void delete(String id);
}
