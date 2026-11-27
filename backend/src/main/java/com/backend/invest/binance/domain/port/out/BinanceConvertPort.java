package com.backend.invest.binance.domain.port.out;

import com.backend.invest.binance.domain.CryptoConvertResult;
import com.backend.invest.binance.domain.port.in.command.ConvertCryptoCommand;

public interface BinanceConvertPort {
  CryptoConvertResult convert(ConvertCryptoCommand command);
}
