package com.dscreate_app.cryptocompare.domain.usecases

import com.dscreate_app.cryptocompare.domain.ICoinRepository
import javax.inject.Inject

class LoadDataUseCase @Inject constructor (
    private val repository: ICoinRepository
) {
    operator fun invoke() = repository.loadData()
}