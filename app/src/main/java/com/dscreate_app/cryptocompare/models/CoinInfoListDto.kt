package com.dscreate_app.cryptocompare.models

import com.google.gson.annotations.SerializedName

data class CoinInfoListDto(
    @SerializedName("Data") val coinInfoContainer: List<CoinInfoContainerDto>? = null
)
