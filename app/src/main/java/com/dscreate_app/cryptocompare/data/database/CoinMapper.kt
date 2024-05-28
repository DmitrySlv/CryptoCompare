package com.dscreate_app.cryptocompare.data.database

import com.dscreate_app.cryptocompare.data.database.model.CoinDbModel
import com.dscreate_app.cryptocompare.data.network.models.CoinInfoContainerDto
import com.dscreate_app.cryptocompare.data.network.models.CoinInfoDto
import com.dscreate_app.cryptocompare.data.network.models.CoinInfoListDto
import com.dscreate_app.cryptocompare.domain.model.CoinInfoEntity
import com.google.gson.Gson

class CoinMapper {

    fun mapCoinDtoToCoinDbModel(coinInfoDto: CoinInfoDto) = CoinDbModel(
        fromSymbol = coinInfoDto.fromSymbol,
        toSymbol = coinInfoDto.toSymbol,
        price = coinInfoDto.price,
        lastUpdate = coinInfoDto.lastUpdate,
        highDay = coinInfoDto.highDay,
        lowDay = coinInfoDto.lowDay,
        lastMarket = coinInfoDto.lastMarket,
        imageUrl = coinInfoDto.imageUrl
    )

    fun mapJsonContainerToCoinListDto(
        json: CoinInfoContainerDto
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
        lastUpdate = coinDbModel.lastUpdate,
        highDay = coinDbModel.highDay,
        lowDay = coinDbModel.lowDay,
        lastMarket = coinDbModel.lastMarket,
        imageUrl = coinDbModel.imageUrl
    )

    companion object {
        private const val EMPTY_SYMBOL = ""
    }
}