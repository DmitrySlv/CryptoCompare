package com.dscreate_app.cryptocompare.models

import com.google.gson.annotations.SerializedName

data class CoinInfoContainerDto(
    @SerializedName("CoinInfo") val coinName: CoinNameDto? = null
)
