package com.dscreate_app.cryptocompare.models

import com.google.gson.annotations.SerializedName

data class CoinName(
    @SerializedName("Name")
    val name: String
)
