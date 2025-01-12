package com.dscreate_app.cryptocompare.data.network.models

import com.google.gson.annotations.SerializedName

data class CoinNameDto(
    @SerializedName("Name")
    val name: String
)
