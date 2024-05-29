package com.dscreate_app.cryptocompare.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dscreate_app.cryptocompare.domain.usecases.GetCoinInfoListUseCase
import com.dscreate_app.cryptocompare.domain.usecases.GetCoinInfoUseCase
import com.dscreate_app.cryptocompare.domain.usecases.LoadDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    val priceList = getCoinInfoListUseCase()

    fun getDetailInfo(fSymbol: String) = getCoinInfoUseCase(fSymbol)

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }
}