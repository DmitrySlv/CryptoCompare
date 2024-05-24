package com.dscreate_app.cryptocompare

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dscreate_app.cryptocompare.databinding.ActivityMainBinding

class CoinsListActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val viewModel by lazy {
        ViewModelProvider(this)[CoinViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.loadData()
        viewModel.priceList.observe(this) {
            Log.d("CoinsListActivity", it.toString())
        }
    }
}