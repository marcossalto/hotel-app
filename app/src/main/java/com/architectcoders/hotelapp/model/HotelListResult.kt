package com.architectcoders.hotelapp.model

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
)

data class OptimizedThumUrlsSerializer(
    val srpDesktop: String = ""
)

data class AddressSerializer(
    val streetAddress: String = "",
    val locality: String = "",
    val countryName: String = "",
    val countryCode: String = ""
)

data class LandmarksSerializer(
    val label: String = "",
    val distance: String = ""
)

data class RatePlanSerializer(
    val price: Price? = Price(),
    val features: Features? = Features()
)

data class Price(
    val current: String? = ""
)

data class Features(
    val paymentPreference: Boolean? = false,
    val noCCRequired: Boolean? = false
)

data class CoordinateSerializer(
    val lat: Double = 0.0,
    val lon: Double = 0.0
)