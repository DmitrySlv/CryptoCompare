package com.dscreate_app.cryptocompare.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dscreate_app.cryptocompare.database.CryptoMainDb
import com.dscreate_app.cryptocompare.models.CoinJsonContainerDto
import com.dscreate_app.cryptocompare.models.CoinPriceInfoDto
import com.dscreate_app.cryptocompare.network.ApiFactory
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val db = CryptoMainDb.getInstance(application)
    val getPriceList = db.dao().getPriceList()

    private val compositeDisposable = CompositeDisposable()

    init {
        loadData()
    }

    fun getCoinDetailInfo(fSym: String): LiveData<CoinPriceInfoDto> {
        return db.dao().getFullCoinInfo(fSym)
    }

    private fun loadData() {
        val disposable = ApiFactory.apiService.getTopListInfo(limit = 30)
            .map { coinInfoList ->
                coinInfoList.coinInfoContainer?.map { it.coinName?.name }?.joinToString(",")
            }
            .flatMap { ApiFactory.apiService.getFullPriceInfo(fSyms = it) }
            .map { getPriceListFromJson(it) }
            .delaySubscription(10,TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    db.dao().insertPriceList(it)
                    Log.d("Test", it.toString())
                }, { throwable ->
                    Log.d("Test", throwable.message.toString())
                }
            )
        compositeDisposable.add(disposable)
    }

    private fun getPriceListFromJson(coinJsonContainer: CoinJsonContainerDto): List<CoinPriceInfoDto> {
        val result = mutableListOf<CoinPriceInfoDto>()

        val jsonObject = coinJsonContainer.jsonObject ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinPriceInfoDto::class.java
                )
                result.add(priceInfo)
            }
        }

        return result
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}