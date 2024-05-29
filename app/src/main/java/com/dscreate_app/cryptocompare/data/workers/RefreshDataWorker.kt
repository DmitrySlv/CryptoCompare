package com.dscreate_app.cryptocompare.data.workers

import android.app.Application
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.dscreate_app.cryptocompare.data.database.AppDatabase
import com.dscreate_app.cryptocompare.data.database.CoinMapper
import com.dscreate_app.cryptocompare.data.network.ApiFactory
import kotlinx.coroutines.delay

class RefreshDataWorker(
    context: Context,
    params: WorkerParameters,
) : CoroutineWorker(context, params) {

    private val dao = AppDatabase.getInstance(context as Application).getDao()
    private val mapper = CoinMapper()

    override suspend fun doWork(): Result {
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

    companion object {
        const val WORKER_NAME = "RefreshDataWorker"

        fun workRequest(): OneTimeWorkRequest {
            return OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        }
    }
}