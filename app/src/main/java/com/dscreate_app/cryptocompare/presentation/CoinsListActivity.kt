package com.dscreate_app.cryptocompare.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dscreate_app.cryptocompare.databinding.ActivityCoinListBinding
import com.dscreate_app.cryptocompare.domain.model.CoinInfoEntity
import com.dscreate_app.cryptocompare.presentation.adapter.CoinAdapter

class CoinsListActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCoinListBinding.inflate(layoutInflater) }

    private val viewModel by lazy {
        ViewModelProvider(this)[CoinViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    private fun init() = with(binding) {
        val adapter = CoinAdapter()
        rvCoinPriceList.layoutManager = LinearLayoutManager(this@CoinsListActivity)
        rvCoinPriceList.adapter = adapter
        rvCoinPriceList.itemAnimator = null
        viewModel.priceList.observe(this@CoinsListActivity) {
            adapter.submitList(it)
        }
        adapter.onCoinClickListener = object : CoinAdapter.OnCoinClickListener {
            override fun onCoinClick(coinInfo: CoinInfoEntity) {
                val intent =
                    CoinDetailActivity.newIntent(this@CoinsListActivity, coinInfo.fromSymbol)
                startActivity(intent)
            }
        }
    }
}