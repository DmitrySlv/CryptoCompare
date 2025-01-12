package com.dscreate_app.cryptocompare

import android.app.Application
import androidx.work.Configuration
import com.dscreate_app.cryptocompare.data.database.AppDatabase
import com.dscreate_app.cryptocompare.data.database.CoinMapper
import com.dscreate_app.cryptocompare.data.network.ApiFactory
import com.dscreate_app.cryptocompare.data.workers.WorkersFactory
import com.dscreate_app.cryptocompare.di.DaggerApplicationComponent
import javax.inject.Inject

class CoinApp: Application(), Configuration.Provider {

    @Inject
    lateinit var workersFactory: WorkersFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workersFactory)
            .build()
}