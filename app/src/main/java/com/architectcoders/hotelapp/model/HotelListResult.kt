package com.architectcoders.hotelapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class HotelListSerializer(
    val result: String = "",
    val data: DataSerializer = DataSerializer()
)

data class DataSerializer(
    val body: BodySerializer = BodySerializer(),
)

data class BodySerializer(
    val searchResults: SearchResultSerializer = SearchResultSerializer()
)

data class SearchResultSerializer(
    val totalCount: String = "0",
    val results: List<HotelSerializer> = listOf()
)

@Parcelize
data class HotelSerializer(
    val id: Int,
    val name: String = "",
    val starRating: Double = 1.0,
    val address: AddressSerializer = AddressSerializer() ,
    val landmarks: List<LandmarksSerializer>,
    val ratePlan: RatePlanSerializer? = RatePlanSerializer(),
    val neighbourhood: String = "",
    val coordinate: CoordinateSerializer = CoordinateSerializer(),
    val optimizedThumbUrls: OptimizedThumUrlsSerializer = OptimizedThumUrlsSerializer()
) : Parcelable

@Parcelize
data class OptimizedThumUrlsSerializer(
    val srpDesktop: String = ""
) : Parcelable

@Parcelize
data class AddressSerializer(
    val streetAddress: String = "",
    val locality: String = "",
    val countryName: String = "",
    val countryCode: String = ""
) : Parcelable

@Parcelize
data class LandmarksSerializer(
    val label: String = "",
    val distance: String = ""
) : Parcelable

@Parcelize
data class RatePlanSerializer(
    val price: Price? = Price(),
    val features: Features? = Features()
) : Parcelable

@Parcelize
data class Price(
    val current: String? = ""
) : Parcelable

@Parcelize
data class Features(
    val paymentPreference: Boolean? = false,
    val noCCRequired: Boolean? = false
) : Parcelable

@Parcelize
data class CoordinateSerializer(
    val lat: Double = 0.0,
    val lon: Double = 0.0
) : Parcelable