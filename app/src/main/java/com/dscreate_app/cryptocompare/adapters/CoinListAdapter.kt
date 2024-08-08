package com.dscreate_app.cryptocompare.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dscreate_app.cryptocompare.R
import com.dscreate_app.cryptocompare.databinding.ItemCoinInfoBinding
import com.dscreate_app.cryptocompare.models.CoinPriceInfoDto
import com.squareup.picasso.Picasso

class CoinListAdapter: ListAdapter<CoinPriceInfoDto, CoinListAdapter.CoinViewHolder>(Comparator) {

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = ItemCoinInfoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CoinViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        onCoinClickListener?.let {
            holder.setData(getItem(position), it)
        }

    }

    class CoinViewHolder(private val binding: ItemCoinInfoBinding): ViewHolder(binding.root) {

        fun setData(
            coinItem: CoinPriceInfoDto,
            onCoinClickListener: OnCoinClickListener
        ) = with(binding) {
            coinItem.apply {
                val symbolsTemplate = root.context.getString(R.string.symbols_template)
                val lastUpdateTemplate = root.context.getString(R.string.last_update_template)

                tvPrice.text = price.toString()
                tvSymbols.text = String.format(symbolsTemplate, fromSymbol, toSymbol)
                tvLastUpdate.text = String.format(lastUpdateTemplate, getFormattedTime())
                Picasso.get().load(getFullImageUrl()).into(imLogoCoin)

                itemView.setOnClickListener {
                    onCoinClickListener.onClick(coinItem)
                }
            }
        }
    }

    object Comparator: DiffUtil.ItemCallback<CoinPriceInfoDto>() {

        override fun areItemsTheSame(
            oldItem: CoinPriceInfoDto,
            newItem: CoinPriceInfoDto,
        ): Boolean {
            return oldItem.fromSymbol == newItem.fromSymbol
        }

        override fun areContentsTheSame(
            oldItem: CoinPriceInfoDto,
            newItem: CoinPriceInfoDto,
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface OnCoinClickListener {
        fun onClick(coinItem: CoinPriceInfoDto)
    }
}