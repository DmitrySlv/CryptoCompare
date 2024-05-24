package com.dscreate_app.cryptocompare

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.dscreate_app.cryptocompare.database.AppDatabase
import com.dscreate_app.cryptocompare.models.CoinInfo
import com.dscreate_app.cryptocompare.models.CoinInfoContainer
import com.dscreate_app.cryptocompare.network.ApiFactory
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)

    val priceList = db.getDao().getPriceList()
    private val compositeDisposable = CompositeDisposable()

    fun loadData() {
        val disposable = ApiFactory.apiService.getTopCoinsInfo(limit = 20)
            .map { it.data?.map { it.coinInfo?.name }?.joinToString(",") }
            .flatMap { ApiFactory.apiService.getFullInfoAboutCoin(fSyms = it) }
            .map { getCurrencyJson(it) }
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.getDao().insertPriceList(it)
            }, { throwable ->
                Log.d("MyLog", throwable.message.toString())
            })
        compositeDisposable.add(disposable)
    }

    private fun getCurrencyJson(coinPriceInfo: CoinInfoContainer): List<CoinInfo> {
        val result = mutableListOf<CoinInfo>()
        val jsonObject = coinPriceInfo.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val coinPrice = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfo::class.java
                )
                result.add(coinPrice)
            }
        }
        return result
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}