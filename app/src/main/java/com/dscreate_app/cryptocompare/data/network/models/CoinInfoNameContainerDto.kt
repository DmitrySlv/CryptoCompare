package com.dscreate_app.cryptocompare.data.network.models

import com.google.gson.annotations.SerializedName

data class CoinInfoNameContainerDto(
    @SerializedName("CoinInfo")
    val coinInfo: CoinNameDto? = null
)
