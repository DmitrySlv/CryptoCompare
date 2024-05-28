package com.dscreate_app.cryptocompare.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dscreate_app.cryptocompare.data.database.repository.CoinRepository
import com.dscreate_app.cryptocompare.domain.usecases.GetCoinInfoListUseCase
import com.dscreate_app.cryptocompare.domain.usecases.GetCoinInfoUseCase
import com.dscreate_app.cryptocompare.domain.usecases.LoadDataUseCase
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepository(application)

    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val priceList = getCoinInfoListUseCase()

    fun getDetailInfo(fSymbol: String) = getCoinInfoUseCase(fSymbol)

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }
}