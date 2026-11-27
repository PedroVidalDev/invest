package com.backend.invest.binance.adapter.out.rest.dto;

public record BinanceConvertAcceptResponse(String orderId, Long createTime, String orderStatus) {
}
