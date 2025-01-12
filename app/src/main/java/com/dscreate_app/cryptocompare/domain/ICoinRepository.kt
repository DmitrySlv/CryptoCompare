package com.dscreate_app.cryptocompare.domain

import androidx.lifecycle.LiveData
import com.dscreate_app.cryptocompare.domain.model.CoinInfoEntity

interface ICoinRepository {

    fun getCoinInfoList(): LiveData<List<CoinInfoEntity>>

    fun getCoinInfo(fSymbol: String): LiveData<CoinInfoEntity>

    fun loadData()
}