package com.dscreate_app.cryptocompare.domain

import androidx.lifecycle.LiveData
import com.dscreate_app.cryptocompare.data.network.models.CoinInfoDto

interface ICoinRepository {

    fun getCoinInfoList(): LiveData<List<CoinInfoDto>>

    fun getCoinInfo(fSym: String): LiveData<CoinInfoDto>

    fun loadData()
}