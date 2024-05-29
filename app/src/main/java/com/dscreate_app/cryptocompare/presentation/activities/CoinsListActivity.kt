package com.dscreate_app.cryptocompare.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dscreate_app.cryptocompare.R
import com.dscreate_app.cryptocompare.databinding.ActivityCoinListBinding
import com.dscreate_app.cryptocompare.domain.model.CoinInfoEntity
import com.dscreate_app.cryptocompare.presentation.CoinViewModel
import com.dscreate_app.cryptocompare.presentation.adapter.CoinAdapter
import com.dscreate_app.cryptocompare.presentation.fragments.CoinDetailFragment

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
                if (isOnePaneMode()) {
                    launchCoinDetailActivity(coinInfo)
                } else {
                    launchCoinDetailFragment(coinInfo)
                }
            }
        }
    }

    private fun isOnePaneMode() = binding.fragmentContainer == null

    private fun launchCoinDetailActivity(coinInfo: CoinInfoEntity) {
        val intent = CoinDetailActivity.newIntent(this, coinInfo.fromSymbol)
        startActivity(intent)
    }

    private fun launchCoinDetailFragment(coinInfo: CoinInfoEntity) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, CoinDetailFragment.newInstance(coinInfo.fromSymbol))
            .addToBackStack(null)
            .commit()
    }
}