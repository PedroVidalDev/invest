package com.backend.invest.binance.adapter.out.rest.dto;

import com.backend.invest.binance.domain.CryptoPrice;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)public record BinancePriceResponse(String symbol,Double price){public static BinancePriceResponse from(CryptoPrice price){return new BinancePriceResponse(price.getSymbol(),price.getPrice());}}
