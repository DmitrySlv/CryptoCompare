package com.dscreate_app.cryptocompare.data.database.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.dscreate_app.cryptocompare.data.database.AppDatabase
import com.dscreate_app.cryptocompare.data.database.CoinMapper
import com.dscreate_app.cryptocompare.data.network.ApiFactory
import com.dscreate_app.cryptocompare.domain.ICoinRepository
import com.dscreate_app.cryptocompare.domain.model.CoinInfoEntity
import kotlinx.coroutines.delay

class CoinRepository(application: Application): ICoinRepository {

    private val dao = AppDatabase.getInstance(application).getDao()
    private val mapper = CoinMapper()

    override fun getCoinInfoList(): LiveData<List<CoinInfoEntity>> {
       return dao.getPriceList().map { coinDbModelList ->
            coinDbModelList.map {
                mapper.mapCoinDbModelToCoinInfoEntity(it)
            }
        }
    }

    override fun getCoinInfo(fSymbol: String): LiveData<CoinInfoEntity> {
        return dao.getFullInfoAboutCoin(fSymbol).map {
            mapper.mapCoinDbModelToCoinInfoEntity(it)
        }
    }

    override suspend fun loadData() {
        while (true) {
            try {
                val topCoinsList = ApiFactory.apiService.getTopCoinsInfo(limit = 20)
                val fSymbol = mapper.mapCoinNamesListDtoToString(topCoinsList)
                val jsonContainer = ApiFactory.apiService.getFullInfoAboutCoin(fSyms = fSymbol)
                val coinsNamesList = mapper.mapJsonContainerToCoinListDto(jsonContainer)
                val coinDbModel = coinsNamesList.map { mapper.mapCoinDtoToCoinDbModel(it) }
                dao.insertPriceList(coinDbModel)
            } catch (e: Exception) {
            }
            delay(10000)
        }
    }
}