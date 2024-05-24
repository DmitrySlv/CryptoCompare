package com.dscreate_app.cryptocompare.models

import com.google.gson.annotations.SerializedName

data class CoinInfoList(
    @SerializedName("Data")
    val data: List<CoinInfoNameContainer>? = null
)
