package com.dscreate_app.cryptocompare.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.dscreate_app.cryptocompare.data.database.CoinMapper
import com.dscreate_app.cryptocompare.data.database.Dao
import com.dscreate_app.cryptocompare.data.network.ApiService
import javax.inject.Inject

class WorkersFactory @Inject constructor (
    private val dao: Dao,
    private val mapper: CoinMapper,
    private val apiService: ApiService
): WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters,
    ): ListenableWorker? {
        return RefreshDataWorker(appContext, workerParameters, dao, mapper, apiService)
    }
}