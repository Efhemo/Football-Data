package com.efhems.football.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface Service {

    //https://api.football-data.org/v2/matches
    //https://api.football-data.org/v2/competitions/?areas=2077 europe


    @GET("matches")
    suspend fun todayFixtures(@Header("X-Auth-Token") auth: String): Response<ResponseBody>

    @GET("competitions")
    suspend fun competitions(
        @Header("X-Auth-Token") auth: String,
        @Query("areas") areas: Int,
        @Query("plan") plan: String
    ): Response<ResponseBody>

    @GET("competitions/{id}/standings")
    suspend fun table(
        @Header("X-Auth-Token") auth: String,
        @Path("id") id: Long,
        @Query("standingType") sstandingType: String
    ): Response<ResponseBody>


    @GET("competitions/{id}/teams")
    suspend fun team(@Header("X-Auth-Token") auth: String, @Path("id") id: Long): Response<ResponseBody>

}