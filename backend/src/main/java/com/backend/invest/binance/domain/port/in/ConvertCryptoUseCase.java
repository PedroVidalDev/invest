package com.backend.invest.binance.domain.port.in;

import com.backend.invest.binance.domain.CryptoConvertResult;
import com.backend.invest.binance.domain.port.in.command.ConvertCryptoCommand;

public interface ConvertCryptoUseCase {
  CryptoConvertResult convert(ConvertCryptoCommand command);
}
