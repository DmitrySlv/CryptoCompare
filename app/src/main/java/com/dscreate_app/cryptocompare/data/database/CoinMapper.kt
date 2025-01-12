package com.dscreate_app.cryptocompare.data.database

import com.dscreate_app.cryptocompare.data.database.model.CoinDbModel
import com.dscreate_app.cryptocompare.data.network.models.CoinInfoContainerDto
import com.dscreate_app.cryptocompare.data.network.models.CoinInfoDto
import com.dscreate_app.cryptocompare.data.network.models.CoinInfoListDto
import com.dscreate_app.cryptocompare.domain.model.CoinInfoEntity
import com.google.gson.Gson
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

class CoinMapper @Inject constructor() {

    fun mapCoinDtoToCoinDbModel(coinInfoDto: CoinInfoDto) = CoinDbModel(
        fromSymbol = coinInfoDto.fromSymbol,
        toSymbol = coinInfoDto.toSymbol.toString(),
        price = coinInfoDto.price.toString(),
        lastUpdate = coinInfoDto.lastUpdate,
        highDay = coinInfoDto.highDay,
        lowDay = coinInfoDto.lowDay,
        lastMarket = coinInfoDto.lastMarket.toString(),
        imageUrl = BASE_IMAGE_URL + coinInfoDto.imageUrl
    )

    fun mapJsonContainerToCoinListDto(
        json: CoinInfoContainerDto,
    ): MutableList<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = json.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val coinPrice = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinInfoDto::class.java
                )
                result.add(coinPrice)
            }
        }
        return result
    }

    fun mapCoinNamesListDtoToString(coinInfoListDto: CoinInfoListDto): String {
        return coinInfoListDto.data?.map { it.coinInfo?.name }?.joinToString(",") ?: EMPTY_SYMBOL
    }

    fun mapCoinDbModelToCoinInfoEntity(coinDbModel: CoinDbModel) = CoinInfoEntity(
        fromSymbol = coinDbModel.fromSymbol,
        toSymbol = coinDbModel.toSymbol,
        price = coinDbModel.price,
        lastUpdate = coinDbModel.lastUpdate?.let { convertTime(it) },
        highDay = coinDbModel.highDay.toString(),
        lowDay = coinDbModel.lowDay.toString(),
        lastMarket = coinDbModel.lastMarket,
        imageUrl = coinDbModel.imageUrl
    )

    private fun convertTime(timesTamp: Long): String {
        val stamp = Timestamp(timesTamp * 1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    companion object {
        const val BASE_IMAGE_URL = "https://www.cryptocompare.com"
        private const val EMPTY_SYMBOL = ""
    }
}