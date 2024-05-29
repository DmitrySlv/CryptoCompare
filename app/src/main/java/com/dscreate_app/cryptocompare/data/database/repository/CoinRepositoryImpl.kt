package com.dscreate_app.cryptocompare.data.database.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.dscreate_app.cryptocompare.data.database.AppDatabase
import com.dscreate_app.cryptocompare.data.database.CoinMapper
import com.dscreate_app.cryptocompare.data.database.Dao
import com.dscreate_app.cryptocompare.data.workers.RefreshDataWorker
import com.dscreate_app.cryptocompare.domain.ICoinRepository
import com.dscreate_app.cryptocompare.domain.model.CoinInfoEntity
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor (
    private val application: Application,
    private val dao: Dao,
    private val mapper: CoinMapper
) : ICoinRepository {


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

    override fun loadData() {
        val workManager = WorkManager.getInstance(application)
        workManager.enqueueUniqueWork(
            RefreshDataWorker.WORKER_NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.workRequest()
        )
    }
}