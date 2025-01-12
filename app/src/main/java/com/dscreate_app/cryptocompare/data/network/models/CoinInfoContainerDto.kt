package com.dscreate_app.cryptocompare.data.network.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class CoinInfoContainerDto(
    @SerializedName("RAW")
    val json: JsonObject? = null
)
