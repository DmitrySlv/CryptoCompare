package com.dscreate_app.cryptocompare.domain.usecases

import com.dscreate_app.cryptocompare.domain.ICoinRepository

class LoadDataUseCase(
    private val repository: ICoinRepository
) {
    operator fun invoke() = repository.loadData()
}