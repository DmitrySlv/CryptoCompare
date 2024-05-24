package com.dscreate_app.cryptocompare.network

import com.dscreate_app.cryptocompare.models.CoinInfoContainer
import com.dscreate_app.cryptocompare.models.CoinInfoList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top/totalvolfull")
    fun getTopCoinsInfo(
        @Query(API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_LIMIT) limit: Int = LIMIT,
        @Query(QUERY_PARAM_TSYM) tSym: String = CURRENCY
    ): Single<CoinInfoList>

    @GET("pricemultifull")
    fun getFullInfoAboutCoin(
        @Query(API_KEY) apiKey: String = API_KEY,
        @Query(QUERY_PARAM_FSYMS) fSyms: String,
        @Query(QUERY_PARAM_TSYMS) tSyms: String = CURRENCY
    ): Single<CoinInfoContainer>

    companion object {
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TSYM = "tsym"
        private const val LIMIT = 20
        private const val QUERY_PARAM_FSYMS = "fsyms"
        private const val QUERY_PARAM_TSYMS = "tsyms"

        private const val CURRENCY = "USD"
        private const val API_KEY =
            "333eac77a403ae9f16994911b23e3759c8b1ad90de5161e2e5cbcd25b0c838cc"
    }
}