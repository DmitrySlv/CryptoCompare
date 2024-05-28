package com.dscreate_app.cryptocompare.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "full_price_list")
data class CoinDbModel(

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
)