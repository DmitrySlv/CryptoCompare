package com.dscreate_app.cryptocompare

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dscreate_app.cryptocompare.databinding.ActivityMainBinding
import com.dscreate_app.cryptocompare.network.ApiFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CoinsListActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val disposable = ApiFactory.apiService.getFullInfoAboutCoin(fSyms = "BTC")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("MyLog", it.toString())
            }, { throwable ->
                Log.d("MyLog", throwable.message.toString())
            })
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}