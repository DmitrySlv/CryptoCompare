package com.dscreate_app.cryptocompare.models

import com.google.gson.annotations.SerializedName

data class CoinInfo(
    @SerializedName("FROMSYMBOL")
    val fromSymbol: String,

    @SerializedName("TOSYMBOL")
    val toSymbol: String,

    @SerializedName("PRICE")
    val price: String,

    @SerializedName("LASTUPDATE")
    val lastUpdate: Int,

    @SerializedName("HIGHDAY")
    val highDay: Double,

    @SerializedName("LOWDAY")
    val lowDay: Double,

    @SerializedName("LASTMARKET")
    val lastMarket: String,

    @SerializedName("IMAGEURL")
    val imageUrl: String
)