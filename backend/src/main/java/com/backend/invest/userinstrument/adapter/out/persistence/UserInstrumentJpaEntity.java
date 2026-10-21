package com.backend.invest.userinstrument.adapter.out.persistence;

import java.time.LocalDateTime;

import com.backend.invest.userinstrument.domain.UserInstrument;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "UserInstrument")
@Table(name = "tb_user_instruments")
@Getter
@AllArgsConstructor 
@NoArgsConstructor 
public class UserInstrumentJpaEntity {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String userId;
    private String instrumentId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserInstrumentJpaEntity from(UserInstrument userInstrument) {
        return new UserInstrumentJpaEntity(
                userInstrument.getId(),
                userInstrument.getUserId(),
                userInstrument.getInstrumentId(),
                userInstrument.getCreatedAt(),
                userInstrument.getUpdatedAt()
        );
    }

    public UserInstrument toDomain() {
        return new UserInstrument(
                id,
                userId,
                instrumentId,
                createdAt,
                updatedAt
        );
    }
}
