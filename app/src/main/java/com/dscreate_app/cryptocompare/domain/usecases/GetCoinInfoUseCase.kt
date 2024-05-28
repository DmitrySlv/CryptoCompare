package com.dscreate_app.cryptocompare.domain.usecases

import com.dscreate_app.cryptocompare.domain.ICoinRepository

class GetCoinInfoUseCase(
  private val repository: ICoinRepository
) {
    operator fun invoke(fSymbol: String) = repository.getCoinInfo(fSymbol)
}