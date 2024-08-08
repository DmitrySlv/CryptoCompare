package com.dscreate_app.cryptocompare.network

import com.dscreate_app.cryptocompare.models.CoinInfoListDto
import com.dscreate_app.cryptocompare.models.CoinJsonContainerDto
import retrofit2.http.GET
import io.reactivex.Single
import retrofit2.http.Query

interface ApiService {

    @GET("top/totalvolfull")
    fun getTopListInfo(
        @Query(QUERY_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_LIMIT) limit: Int = LIMIT,
        @Query(QUERY_TO_SYMBOL) tSym: String = CURRENCY_NAME
        ): Single<CoinInfoListDto>

    @GET("pricemultifull")
    fun getFullPriceInfo(
        @Query(QUERY_API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_FROM_SYMBOLS) fSyms: String,
        @Query(QUERY_TO_SYMBOLS) tSyms: String = CURRENCY_NAME
    ): Single<CoinJsonContainerDto>

    private companion object {
        const val API_KEY = "333eac77a403ae9f16994911b23e3759c8b1ad90de5161e2e5cbcd25b0c838cc"

        const val QUERY_API_KEY = "api_key"
        const val QUERY_LIMIT = "limit"
        const val QUERY_TO_SYMBOL = "tsym"
        const val QUERY_FROM_SYMBOLS = "fsyms"
        const val QUERY_TO_SYMBOLS = "tsyms"

        const val CURRENCY_NAME = "USD"
        const val LIMIT = 30
    }
}