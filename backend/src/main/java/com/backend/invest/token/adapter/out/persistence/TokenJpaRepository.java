package com.backend.invest.token.adapter.out.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.backend.invest.token.domain.Token;
import com.backend.invest.token.domain.port.out.TokenRepositoryPort;

@Repository
public class TokenJpaRepository implements TokenRepositoryPort {
    private final TokenJpaSpringRepository springRepository;

    public TokenJpaRepository(TokenJpaSpringRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public Token save(Token token) {
        return springRepository.save(TokenJpaEntity.from(token)).toDomain();
    }

    @Override
    public Optional<Token> findByUserId(String userId) {
        return springRepository.findByUserId(userId).map(TokenJpaEntity::toDomain);
    }

    @Override
    public void delete(String id) {
        springRepository.deleteById(id);
    }
}
