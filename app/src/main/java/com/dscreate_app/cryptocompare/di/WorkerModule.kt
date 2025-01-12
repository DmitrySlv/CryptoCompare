package com.dscreate_app.cryptocompare.di

import com.dscreate_app.cryptocompare.data.workers.ChildWorkerFactory
import com.dscreate_app.cryptocompare.data.workers.RefreshDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    fun bindRefreshDataWorkerFactory(worker: RefreshDataWorker.Factory): ChildWorkerFactory
}