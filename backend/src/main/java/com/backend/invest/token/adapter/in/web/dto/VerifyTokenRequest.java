package com.backend.invest.token.adapter.in.web.dto;

public record VerifyTokenRequest(
    String userId,
    String code
) {}
