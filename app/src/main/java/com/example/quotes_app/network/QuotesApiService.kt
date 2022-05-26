package com.example.quotes_app.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://zenquotes.io/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface QuotesApiService {
    @GET("quotes")
    suspend fun get():List<Quotes>
}

object QuotesApi {
    val retrofitServiceApi: QuotesApiService by lazy {
        retrofit.create(QuotesApiService::class.java)
    }
}