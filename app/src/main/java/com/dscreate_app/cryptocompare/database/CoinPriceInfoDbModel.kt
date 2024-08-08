package com.dscreate_app.cryptocompare.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coin_price_info")
data class CoinPriceInfoDbModel(
    @PrimaryKey
    val fromSymbol: String,
    val toSymbol: String,
    val market: String,
    val lastMarket: String,
    val price: Double,
    val lastUpdate: Long,
    val highDay: Double,
    val lowDay: Double,
    val imageUrl: String,
)
