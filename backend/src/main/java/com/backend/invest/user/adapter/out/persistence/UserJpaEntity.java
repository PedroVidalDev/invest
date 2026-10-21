package com.backend.invest.user.adapter.out.persistence;

import java.time.LocalDateTime;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.backend.invest.user.domain.CategoryType;
import com.backend.invest.user.domain.User;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Users")
@Table(name = "tb_users")
@Getter 
@AllArgsConstructor 
@NoArgsConstructor 
public class UserJpaEntity {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String email;
    private String password;
    private String photo;
    private boolean active;

    @Enumerated (EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private CategoryType category;

    private LocalDateTime verifiedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserJpaEntity from(com.backend.invest.user.domain.User user) {
        return new UserJpaEntity(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhoto(),
                user.getActive(),
                user.getCategory(),
                user.getVerifiedAt(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public User toDomain() {
        return new User(
            id,
            name,
            email,
            password,
            photo,
            active,
            category,
            verifiedAt,
            createdAt,
            updatedAt
        );
    }
}
