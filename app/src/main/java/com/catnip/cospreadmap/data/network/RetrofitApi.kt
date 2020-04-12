package com.catnip.cospreadmap.data.network

import com.catnip.cospreadmap.BuildConfig
import com.catnip.cospreadmap.data.model.province.Province
import com.catnip.cospreadmap.data.model.spread.global.GlobalSpreadResponse
import com.catnip.cospreadmap.data.model.spread.local.LocalSpreadResponse
import com.catnip.cospreadmap.data.model.total.global.Count
import com.catnip.cospreadmap.data.model.total.local.IndonesiaCount
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
import java.util.concurrent.TimeUnit

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

interface RetrofitApi {
    //TODO : api interface will written in here.
    @GET("indonesia")
    fun getIndonesiaCount(): Observable<List<IndonesiaCount>>

    @GET("indonesia/provinsi")
    fun getLocalSpread(): Observable<List<LocalSpreadResponse>>

    @GET
    fun getIndonesianProvince(@Url url: String = "https://raw.githubusercontent.com/hermasyp/wilayah-indonesia/master/data/list_of_area/provinces.json"): Observable<List<Province>>

    @GET
    fun getGlobalSpread(@Url url: String = BuildConfig.BASE_URL): Observable<List<GlobalSpreadResponse>>

    @GET("positif")
    fun getGlobalPositive(): Observable<Count>

    @GET("sembuh")
    fun getGlobalCured(): Observable<Count>

    @GET("meninggal")
    fun getGlobalDeath(): Observable<Count>


    companion object {
        operator fun invoke(): RetrofitApi {
            val client = OkHttpClient
                .Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RetrofitApi::class.java)
        }
    }
}