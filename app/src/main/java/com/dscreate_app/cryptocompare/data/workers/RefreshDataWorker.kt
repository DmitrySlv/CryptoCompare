package com.dscreate_app.cryptocompare.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.dscreate_app.cryptocompare.data.database.CoinMapper
import com.dscreate_app.cryptocompare.data.database.Dao
import com.dscreate_app.cryptocompare.data.network.ApiService
import kotlinx.coroutines.delay
import javax.inject.Inject

class RefreshDataWorker @Inject constructor (
    context: Context,
    params: WorkerParameters,
    private val dao: Dao,
    private val mapper: CoinMapper,
    private val apiService: ApiService
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoinsList = apiService.getTopCoinsInfo(limit = 20)
                val fSymbol = mapper.mapCoinNamesListDtoToString(topCoinsList)
                val jsonContainer = apiService.getFullInfoAboutCoin(fSyms = fSymbol)
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