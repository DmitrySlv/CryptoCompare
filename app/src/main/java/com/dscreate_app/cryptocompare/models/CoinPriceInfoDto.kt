package com.dscreate_app.cryptocompare.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dscreate_app.cryptocompare.network.ApiFactory.BASE_IMAGE_URL
import com.dscreate_app.cryptocompare.utils.convertTimestampToTime
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coin_price_info")
data class CoinPriceInfoDto(

    @PrimaryKey
    @SerializedName("FROMSYMBOL")
    val fromSymbol: String,

    @SerializedName("TOSYMBOL")
    val toSymbol: String?,

    @SerializedName("MARKET")
    val market: String?,

    @SerializedName("LASTMARKET")
    val lastMarket: String?,

    @SerializedName("PRICE")
    val price: Double?,

    @SerializedName("LASTUPDATE")
    val lastUpdate: Long?,

    @SerializedName("HIGHDAY")
    val highDay: Double?,

    @SerializedName("LOWDAY")
    val lowDay: Double?,

    @SerializedName("IMAGEURL")
    val imageUrl: String?
) {
    fun getFormattedTime(): String {
        return convertTimestampToTime(lastUpdate)
    }

    fun getFullImageUrl(): String {
        return BASE_IMAGE_URL + imageUrl
    }
}
