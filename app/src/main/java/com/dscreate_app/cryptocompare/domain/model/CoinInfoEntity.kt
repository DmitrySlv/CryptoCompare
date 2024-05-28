package com.dscreate_app.cryptocompare.domain.model

data class CoinInfoEntity(
val fromSymbol: String,
val toSymbol: String?,
val price: String?,
val lastUpdate: String?,
val highDay: String?,
val lowDay: String?,
val lastMarket: String?,
val imageUrl: String
)
