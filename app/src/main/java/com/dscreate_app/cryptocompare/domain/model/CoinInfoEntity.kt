package com.dscreate_app.cryptocompare.domain.model

data class CoinInfoEntity(
val fromSymbol: String,
val toSymbol: String,
val price: String,
val lastUpdate: Long,
val highDay: Double,
val lowDay: Double,
val lastMarket: String,
val imageUrl: String
)
