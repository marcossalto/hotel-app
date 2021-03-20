package com.architectcoders.hotelapp.model

import com.architectcoders.hotelapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers(
        "x-rapidapi-key: ${BuildConfig.API_KEY}",
        "x-rapidapi-host: ${BuildConfig.API_HOST}"
    )
    @GET("${BuildConfig.API_BASE_URL}/get-meta-data")
    suspend fun listCountries(): List<Country>

    @Headers(
        "x-rapidapi-key: ${BuildConfig.API_KEY}",
        "x-rapidapi-host: ${BuildConfig.API_HOST}"
    )
    @GET("${BuildConfig.API_BASE_URL}/locations/search")
    suspend fun searchDestination(@Query("query") query: String
                                  , @Query("locale") locale: String
    ): DestinationResult

    @Headers(
        "x-rapidapi-key: ${BuildConfig.API_KEY}",
        "x-rapidapi-host: ${BuildConfig.API_HOST}"
    )

    @GET("${BuildConfig.API_BASE_URL}/properties/list")
    suspend fun listHotels(@Query("destinationId") destinationId: Int
                           , @Query("pageNumber") pageNumber: String = ""
                           , @Query("pageSize") pageSize: String = ""
                           , @Query("currency") currency: String = ""
                           , @Query("locale") locale: String = ""
                           , @Query("sortOrder") sortOrder: String = ""
                           , @Query("checkIn") checkIn: String = ""
                           , @Query("checkOut") checkOut: String = ""
                           , @Query("adults1") adults1: String = ""
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

    @Headers(
        "x-rapidapi-key: ${BuildConfig.API_KEY}",
        "x-rapidapi-host: ${BuildConfig.API_HOST}"
    )
    @GET("${BuildConfig.API_BASE_URL}/properties/get-details")
    suspend fun getDetail(@Query("id") id: Int
                          , @Query("locale") locale: String = ""
                          , @Query("currency") currency: String = ""
                          , @Query("checkIn") checkIn: String = ""
                          , @Query("checkOut") checkOut: String = ""
                          , @Query("adults1") adults1: String = ""
    ): HotelDetailSerializer
}