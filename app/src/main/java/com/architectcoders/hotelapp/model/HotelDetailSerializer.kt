package com.architectcoders.hotelapp.model

import com.google.gson.JsonArray
import com.google.gson.JsonObject

data class HotelDetailSerializer(
    val result: String = "",
    val data: DataDetailSerializer,
    val neighborhood: NeighborhoodDetailSerializer,
    val transportation: TransportationDetailSerializer
)

data class DataDetailSerializer(
    val body: BodyDetailSerializer,
    val common: CommonDetailSerializer,
)

data class NeighborhoodDetailSerializer(
    val neighborhoodName: String = "",
)

data class TransportationDetailSerializer(
    val transportLocations: List<TransportLocationSerializer>
)

data class TransportLocationSerializer(
    val category: String = "",
    val locations: List<LocationSerializer>
)

data class LocationSerializer(
    val distance: String = "",
    val distanceInTime: String = "",
    val name: String = ""
)

data class BodyDetailSerializer(
    val amenities: List<Amenity>,
    val atAGlance: AtAGlance,
    val guestReview: GuestReview,
    val hotelBadge: HotelBadge,
    val hotelWelcomeRewards: HotelWelcomeRewards,
    val hygieneAndCleanliness: HygieneAndCleanliness,
    val miscellaneous: Miscellaneous,
    val overview: Overview,
    val pageInfo: PageInfoSerializer,
    val pdpHeader: PdpHeader,
    val propertyDescription: PropertyDescription,
    val smallPrint: SmallPrint,
    val specialFeatures: SpecialFeatures,
    val unavailable: JsonObject
)

data class Amenity(
    val heading: String = "",
    val listItems: List<AmenityDescriptor>
)

data class AmenityDescriptor(
    val heading: String = "",
    val listItems: List<String>
)

data class AtAGlance(
    val keyFacts: KeyFact,
    val transportAndOther: TransportAndOther,
    val travellingOrInternet: TravellingOrInternet
)

data class KeyFact(
    val arrivingLeaving: List<String>,
    val hotelSize: List<String>,
    val requiredAtCheckIn: List<String>,
    val specialCheckInInstructions: List<String>,
)

data class TransportAndOther(
    val otherInclusions: List<String>,
    val otherInformation: List<String>,
    val transport: TransportSerializer
)

data class TransportSerializer(
    val offsiteTransfer: List<String>,
    val parking: List<String>,
    val transfers: List<String>,
)

data class TravellingOrInternet(
    val internet: List<String>,
    val travelling: TravellingSerializer
)

data class TravellingSerializer(
    val children: List<String>,
    val extraPeople: List<String>,
    val pets: List<String>,
)

data class GuestReview(
    val brands: Brand
)

data class Brand(
    val badgeText: String = "",
    val formattedRating: String = "",
    val formattedScale: String = "",
    val lowRating: Boolean = false,
    val rating: Double = 0.0,
    val scale: Double = 0.0,
    val total: Long = 0
)

data class HotelBadge(
    val label: String = "",
    val tooltipText: String = "",
    val tooltipTitle: String = "",
    val type: String = "",
)

data class HotelWelcomeRewards(
    val applies: Boolean = false,
    val info: String = ""
)

data class HygieneAndCleanliness(
    val healthAndSafetyMeasures: HealthAndSafetyMeasures,
    val hygieneQualifications: HygieneQualifications,
    val title: String = ""
)

data class HealthAndSafetyMeasures(
    val description: String = "",
    val measures: List<String>,
    val title: String = ""
)

data class HygieneQualifications(
    val qualifications: List<String>,
    val title: String = ""
)

data class Miscellaneous(
    val legalInfoForStrikethroughPrices: String = "",
    val pimmsAttributes: String = "",
    val showLegalInfoForStrikethroughPrices: Boolean = false
)

data class Overview(
    val overviewSections: List<OverviewSections>
)

data class OverviewSections(
    val content: List<String>,
    val contentType: String = "",
    val title: String = "",
    val type: String = ""
)

data class PageInfoSerializer(
    val errorKeys: List<String>,
    val errors: List<Errors>,
    val pageType: String = ""
)

data class Errors(
    val errorMessages: List<String>,
    val fieldName: String = ""
)

data class PdpHeader(
    val currencyCode: String = "",
    val destinationId: String = "",
    val hotelId: String = "",
    val hotelLocation: HotelLocation,
    val occupancyKey: String = "",
    val pointOfSaleId: String = "",
)

data class HotelLocation(
    val coordinates: Coordinates,
    val locationName: String = "",
    val resolvedLocation: String = "",
)

data class Coordinates(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)

data class PropertyDescription(
    val address: Address,
    val clientToken: String = "",
    val featuredPrice: FeaturePrice,
    val freebies: List<String>,
    val mapWidget: MapWidget,
    val name: String = "",
    val priceMatchEnabled: Boolean = false,
    val roomTypeNames: List<String>,
    val starRating: Double = 0.0,
    val starRatingTitle: String = "",
    val tagline: List<String>
)

data class MapWidget(
    val staticMapUrl: String = ""
)

data class FeaturePrice(
    val afterPriceText: String = "",
    val beforePriceText: String = "",
    val bookNowButton: Boolean = false,
    val currentPrice: CurrentPrice
)

data class CurrentPrice(
    val formatted: String = "",
    val plain: Double = 0.0,
    val pricingAvailability: String = "",
    val pricingTooltip: String = "",
    val taxInclusiveFormatting: Boolean = false
)

data class Address(
    val addressLine1: String = "",
    val cityName: String = "",
    val countryCode: String = "",
    val countryName: String = "",
    val fullAddress: String = "",
    val pattern: String = "",
    val postalCode: String = "",
    val provinceName: String = ""
)

data class SmallPrint(
    val alternativeNames: List<String>,
    val display: Boolean = false,
    val mandatoryFees: List<String>,
    val mandatoryTaxesOrFees: Boolean = false,
    val optionalExtras: List<String>,
    val policies: List<String>
)

data class SpecialFeatures(
    val sections: List<Section>
)

data class Section(
    val freeText: String = "",
    val heading: String = "",
    val listItems: JsonArray,
    val subsections: JsonArray
)

data class CommonDetailSerializer(
    val pointOfSale: PointOfSale,
    val tracking: Tracking
)

data class PointOfSale(
    val brandName: String = "",
    val numberSeparators: String = "",
)

data class Tracking(
    val omniture: Omniture,
    val pageViewBeaconUrl: String = ""
)

data class Omniture(
    val scurrencyCode: String = "",
    val seVar13: String = "",
    val seVar16: String = "",
    val seVar26: String = "",
    val seVar29: String = "",
    val seVar31: String = "",
    val seVar32: String = "",
    val seVar34: String = "",
    val seVar4: String = "",
    val seVar40: String = "",
    val seVar41: String = "",
    val seVar43: String = "",
    val seVar69: String = "",
    val seVar80: String = "",
    val seVar93: String = "",
    val seVar95: String = "",
    val sproducts: String = "",
    val sprop27: String = "",
    val sprop28: String = "",
    val sprop34: String = "",
    val sprop36: String = "",
    val sprop48: String = "",
    val sprop5: String = "",
    val sserver: String = ""
)
