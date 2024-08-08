package com.dscreate_app.cryptocompare.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dscreate_app.cryptocompare.models.CoinPriceInfoDto

@Database(entities = [CoinPriceInfoDto::class], version = 1, exportSchema = false)
abstract class CryptoMainDb : RoomDatabase() {

    companion object {
        private var INSTANCE: CryptoMainDb? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(application: Application): CryptoMainDb {
            INSTANCE?.let { return it }
            synchronized(LOCK) {
                INSTANCE?.let { return it }
                val instance = Room.databaseBuilder(
                    application,
                    CryptoMainDb::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    abstract fun dao(): CoinDao
}