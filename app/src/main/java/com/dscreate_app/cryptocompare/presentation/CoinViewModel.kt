package com.dscreate_app.cryptocompare.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dscreate_app.cryptocompare.data.database.AppDatabase
import com.dscreate_app.cryptocompare.data.network.models.CoinInfoDto
import com.dscreate_app.cryptocompare.data.network.models.CoinInfoContainerDto
import com.dscreate_app.cryptocompare.data.network.ApiFactory
import com.dscreate_app.cryptocompare.domain.usecases.GetCoinInfoListUseCase
import com.dscreate_app.cryptocompare.domain.usecases.GetCoinInfoUseCase
import com.dscreate_app.cryptocompare.domain.usecases.LoadDataUseCase
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)


    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val getCoinInfoListUseCase = GetCoinInfoListUseCase(repository)
    private val loadData = LoadDataUseCase(repository)

    val priceList = db.getDao().getPriceList()

    fun getDetailInfo(fSym: String): LiveData<CoinInfoDto> = db.getDao().getFullInfoAboutCoin(fSym)

    init {
        loadData()
    }

   private fun loadData() {
        val disposable = ApiFactory.apiService.getTopCoinsInfo(limit = 20)
            .map { it.data?.map { it.coinInfo?.name }?.joinToString(",") }
            .flatMap { ApiFactory.apiService.getFullInfoAboutCoin(fSyms = it) }
            .map { getCurrencyJson(it) }
            .delaySubscription(10, TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.getDao().insertPriceList(it)
            }, { throwable ->
            })
    }

    private fun getCurrencyJson(coinPriceInfo: CoinInfoContainerDto): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = coinPriceInfo.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val coinPrice = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(coinPrice)
            }
        }
        return result
    }
}