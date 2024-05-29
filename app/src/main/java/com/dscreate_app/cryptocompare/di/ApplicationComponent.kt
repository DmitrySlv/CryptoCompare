package com.dscreate_app.cryptocompare.di

import android.app.Application
import com.dscreate_app.cryptocompare.CoinApp
import com.dscreate_app.cryptocompare.presentation.activities.CoinsListActivity
import com.dscreate_app.cryptocompare.presentation.fragments.CoinDetailFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(application: CoinApp)
    fun inject(activity: CoinsListActivity)
    fun inject(fragment: CoinDetailFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}