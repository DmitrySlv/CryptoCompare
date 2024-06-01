package com.dscreate_app.cryptocompare

import android.app.Application
import androidx.work.Configuration
import com.dscreate_app.cryptocompare.data.database.AppDatabase
import com.dscreate_app.cryptocompare.data.database.CoinMapper
import com.dscreate_app.cryptocompare.data.network.ApiFactory
import com.dscreate_app.cryptocompare.data.workers.WorkersFactory
import com.dscreate_app.cryptocompare.di.DaggerApplicationComponent

class CoinApp: Application(), Configuration.Provider {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(
               WorkersFactory(
                   AppDatabase.getInstance(this).getDao(),
                   CoinMapper(),
                   ApiFactory.apiService
               )
            )
            .build()
}