package com.dscreate_app.cryptocompare.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dscreate_app.cryptocompare.CoinApp
import com.dscreate_app.cryptocompare.databinding.FragmentCoinDetailBinding
import com.dscreate_app.cryptocompare.presentation.view_models.CoinViewModel
import com.dscreate_app.cryptocompare.presentation.view_models.ViewModelFactory
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CoinDetailFragment : Fragment() {

    private var _binding: FragmentCoinDetailBinding? = null
    private val binding: FragmentCoinDetailBinding
        get() = _binding ?: throw RuntimeException("CoinDetailFragment is null")

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as CoinApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun init() = with(binding) {
        val fSymbol = getSymbol()
        viewModel.getDetailInfo(fSymbol).observe(viewLifecycleOwner) {
            tvFromSymbol.text = it.fromSymbol
            tvToSymbol.text = it.toSymbol
            tvPrice.text = it.price
            tvMinPrice.text = it.lowDay.toString()
            tvMaxPrice.text = it.highDay.toString()
            tvLastMarket.text = it.lastMarket
            tvLastUpdate.text = it.lastUpdate
            Picasso.get().load(it.imageUrl).into(ivLogoCoin)
        }
    }

    private fun getSymbol(): String {
        return requireArguments().getString(EXTRA_FROM_SYMBOL, EMPTY_SYMBOL)
    }

    companion object {
        const val EXTRA_FROM_SYMBOL = "fSym"
        private const val EMPTY_SYMBOL = ""

        @JvmStatic
        fun newInstance(fSymbol: String): CoinDetailFragment {
            return CoinDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_FROM_SYMBOL, fSymbol)
                }
            }
        }
    }
}