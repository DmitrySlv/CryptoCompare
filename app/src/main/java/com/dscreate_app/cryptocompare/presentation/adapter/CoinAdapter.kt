package com.dscreate_app.cryptocompare.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dscreate_app.cryptocompare.R
import com.dscreate_app.cryptocompare.data.network.ApiFactory.BASE_IMAGE_URL
import com.dscreate_app.cryptocompare.databinding.ItemCoinInfoBinding
import com.dscreate_app.cryptocompare.domain.model.CoinInfoEntity
import com.squareup.picasso.Picasso

class CoinAdapter: ListAdapter<CoinInfoEntity, CoinAdapter.CoinViewHolder>(CoinDiffUtil) {

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = ItemCoinInfoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CoinViewHolder(binding, onCoinClickListener)
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.setData(getItem(position))
    }


    class CoinViewHolder(
        private val binding: ItemCoinInfoBinding,
        private val onCoinClickListener: OnCoinClickListener?
    ): ViewHolder(binding.root) {

        fun setData(coinInfo: CoinInfoEntity) = with(binding) {
            coinInfo.apply {
                val symbolsTemplate = root.context.getString(R.string.symbols_template)
                val lastUpdateTemplate = root.context.getString(R.string.last_update_template)

                tvSymbols.text = String.format(symbolsTemplate, fromSymbol, toSymbol)
                tvPrice.text = price
                tvLastUpdate.text  = String.format(lastUpdateTemplate, getFormattedTime())
                Picasso.get().load(BASE_IMAGE_URL + imageUrl).into(ivLogoCoin)

                itemView.setOnClickListener {
                    onCoinClickListener?.onCoinClick(coinInfo)
                }
            }
        }
    }

    object CoinDiffUtil: ItemCallback<CoinInfoEntity>() {

        override fun areItemsTheSame(oldItem: CoinInfoEntity, newItem: CoinInfoEntity): Boolean {
            return oldItem.fromSymbol == newItem.fromSymbol
        }

        override fun areContentsTheSame(oldItem: CoinInfoEntity, newItem: CoinInfoEntity): Boolean {
            return oldItem == newItem
        }
    }

    interface OnCoinClickListener {
        fun onCoinClick(coinInfo: CoinInfoEntity)
    }
}