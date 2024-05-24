package com.dscreate_app.cryptocompare.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dscreate_app.cryptocompare.models.CoinInfo

@Dao
interface Dao {

    @Query("SELECT *  FROM full_price_list ORDER BY lastUpdate DESC")
    fun getPriceList(): LiveData<List<CoinInfo>>

    @Query("SELECT * FROM full_price_list WHERE fromSymbol IS :fSym LIMIT 1")
    fun getFullInfoAboutCoin(fSym: String): LiveData<CoinInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPriceList(priceList: List<CoinInfo>)
}