package com.architectcoders.hotelapp

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers(
            "x-rapidapi-key: $API_KEY",
            "x-rapidapi-host: $API_HOST"
    )
    @GET("$API_BASE_URL/get-meta-data")
    suspend fun listCountries(): List<Country>

    @Headers(
            "x-rapidapi-key: $API_KEY",
            "x-rapidapi-host: $API_HOST"
    )
    @GET("$API_BASE_URL/locations/search")
    suspend fun searchDestination(@Query("query") query: String, @Query("locale") locale: String): DestinationResult
}