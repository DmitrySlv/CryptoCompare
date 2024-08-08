package com.dscreate_app.cryptocompare.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dscreate_app.cryptocompare.models.CoinPriceInfoDto

@Dao
interface CoinDao {

    @Query("SELECT * FROM coin_price_info ORDER BY lastUpdate DESC")
    fun getPriceList(): LiveData<List<CoinPriceInfoDto>>

    @Query("SELECT * FROM coin_price_info WHERE fromSymbol IS :fSym LIMIT 1")
    fun getFullCoinInfo(fSym: String): LiveData<CoinPriceInfoDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPriceList(priceList: List<CoinPriceInfoDto>)
}