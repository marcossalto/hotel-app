package com.architectcoders.hotelapp.model

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.QueryMap

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
    suspend fun searchDestination(@Query("query") query: String
                                  , @Query("locale") locale: String
    ): DestinationResult

    @Headers(
        "x-rapidapi-key: $API_KEY",
        "x-rapidapi-host: $API_HOST"
    )
    @GET("$API_BASE_URL/properties/list")
    suspend fun listHotels(@Query("destinationId") destinationId: Int = 10234237
                           , @Query("pageNumber") pageNumber: String = "1"
                           , @Query("pageSize") pageSize: String = "10"
                           , @Query("currency") currency: String = "USD"
                           , @Query("locale") locale: String = "es_ES"
                           , @Query("sortOrder") sortOrder: String = "PRICE"
                           , @Query("checkIn") checkIn: String = "2020-02-20"
                           , @Query("checkOut") checkOut: String = "2020-02-28"
                           , @Query("adults1") adults1: String = "1"
                           , @Query("adults2") adults2: String = ""
                           , @Query("adults3") adults3: String = ""
                           , @Query("adults4") adults4: String = ""
                           , @Query("adults5") adults5: String = ""
                           , @Query("adults6") adults6: String = ""
                           , @Query("adults7") adults7: String = ""
                           , @Query("adults8") adults8: String = ""
                           , @Query("children1") children1: String = ""
                           , @Query("children2") children2: String = ""
                           , @Query("children3") children3: String = ""
                           , @Query("children4") children4: String = ""
                           , @Query("children5") children5: String = ""
                           , @Query("children6") children6: String = ""
                           , @Query("children7") children7: String = ""
                           , @Query("children8") children8: String = ""
                           , @Query("accommodationsIds") accommodationsIds: String = ""
                           , @Query("guestRatingMin") guestRatingMin: String = ""
                           , @Query("landmarkIds") landmarkIds: String = ""
                           , @Query("priceMax") priceMax: String = ""
                           , @Query("priceMin") priceMin: String = ""
                           , @Query("startRatings") startRatings: String = ""
                           , @Query("themelds") themelds: String = ""
    ): HotelListSerializer
}