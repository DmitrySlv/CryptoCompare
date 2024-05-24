package com.dscreate_app.cryptocompare.models

import com.google.gson.annotations.SerializedName

data class CoinInfoNameContainer(
    @SerializedName("CoinInfo")
    val data: CoinName? = null
)
