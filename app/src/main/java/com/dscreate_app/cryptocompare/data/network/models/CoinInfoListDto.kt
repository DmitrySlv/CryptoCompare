package com.dscreate_app.cryptocompare.data.network.models

import com.google.gson.annotations.SerializedName

data class CoinInfoListDto(
    @SerializedName("Data")
    val data: List<CoinInfoNameContainerDto>? = null
)
