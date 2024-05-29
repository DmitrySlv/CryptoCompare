package com.dscreate_app.cryptocompare.di

import android.app.Application
import com.dscreate_app.cryptocompare.data.database.AppDatabase
import com.dscreate_app.cryptocompare.data.database.Dao
import com.dscreate_app.cryptocompare.data.database.repository.CoinRepositoryImpl
import com.dscreate_app.cryptocompare.domain.ICoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): ICoinRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideDao(application: Application): Dao {
            return AppDatabase.getInstance(application).getDao()
        }
    }

}