package com.backend.invest.token.adapter.in.web.dto;

import com.backend.invest.token.domain.ServiceType;

public record VerifyTokenRequest(
    String userId,
    ServiceType service,
    String code
) {}
