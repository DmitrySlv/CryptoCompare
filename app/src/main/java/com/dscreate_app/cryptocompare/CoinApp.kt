package com.dscreate_app.cryptocompare

import android.app.Application
import com.dscreate_app.cryptocompare.di.DaggerApplicationComponent

class CoinApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}