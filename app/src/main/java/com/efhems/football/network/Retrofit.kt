package com.efhems.football.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


private const val BASE_URL: String = "https://api.football-data.org/v2/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//com.efhems.football.network.interceptor
val interceptor = HttpLoggingInterceptor()
val client: OkHttpClient = OkHttpClient.Builder().
    addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)).build()


object Network {
    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val service: Service = retrofit.create(Service::class.java)
}
