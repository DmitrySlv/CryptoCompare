package com.dscreate_app.cryptocompare.di

import androidx.lifecycle.ViewModel
import com.dscreate_app.cryptocompare.presentation.view_models.CoinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    fun bindCoinViewModel(coinViewModel: CoinViewModel): ViewModel
}