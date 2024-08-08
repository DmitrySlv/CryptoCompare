package com.dscreate_app.cryptocompare

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dscreate_app.cryptocompare.adapters.CoinListAdapter
import com.dscreate_app.cryptocompare.databinding.ActivityCoinsListBinding
import com.dscreate_app.cryptocompare.models.CoinPriceInfoDto
import com.dscreate_app.cryptocompare.view_model.CoinViewModel

class CoinsListActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCoinsListBinding.inflate(layoutInflater) }

    private val viewModel by lazy {
        ViewModelProvider(this)[CoinViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    private fun init() = with(binding) {
        val adapter = CoinListAdapter()
        adapter.onCoinClickListener = object : CoinListAdapter.OnCoinClickListener {
            override fun onClick(coinItem: CoinPriceInfoDto) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinsListActivity, coinItem.fromSymbol
                )
                startActivity(intent)
            }
        }
        rvCoinPriceList.layoutManager = LinearLayoutManager(this@CoinsListActivity)
        rvCoinPriceList.adapter = adapter
        rvCoinPriceList.itemAnimator = null
        viewModel.getPriceList.observe(this@CoinsListActivity) {
            adapter.submitList(it)
        }
    }

}