package com.dscreate_app.cryptocompare.domain.usecases

import com.dscreate_app.cryptocompare.domain.ICoinRepository
import javax.inject.Inject

class GetCoinInfoUseCase @Inject constructor (
  private val repository: ICoinRepository
) {
    operator fun invoke(fSymbol: String) = repository.getCoinInfo(fSymbol)
}