package com.dscreate_app.cryptocompare.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dscreate_app.cryptocompare.network.ApiFactory.BASE_IMAGE_URL
import com.dscreate_app.cryptocompare.utils.convertTime
import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_price_list")
data class CoinInfo(

    @PrimaryKey
    @SerializedName("FROMSYMBOL")
    val fromSymbol: String,

    @SerializedName("TOSYMBOL")
    val toSymbol: String,

    @SerializedName("PRICE")
    val price: String,

    @SerializedName("LASTUPDATE")
    val lastUpdate: Long,

    @SerializedName("HIGHDAY")
    val highDay: Double,

    @SerializedName("LOWDAY")
    val lowDay: Double,

    @SerializedName("LASTMARKET")
    val lastMarket: String,

    @SerializedName("IMAGEURL")
    val imageUrl: String
) {
    fun getFormattedTime(): String {
        return convertTime(lastUpdate)
    }
    fun getFullImageUrl(): String {
        return BASE_IMAGE_URL + imageUrl
    }
}
