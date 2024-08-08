package com.dscreate_app.cryptocompare.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class CoinJsonContainerDto(
    @SerializedName("RAW") val jsonObject: JsonObject? = null
)
