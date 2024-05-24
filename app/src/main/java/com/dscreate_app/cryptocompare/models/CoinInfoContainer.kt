package com.dscreate_app.cryptocompare.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class CoinInfoContainer(
    @SerializedName("RAW")
    val json: JsonObject? = null
)
