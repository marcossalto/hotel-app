package com.architectcoders.hotelapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class SelectedSearch (
    var hotel: HotelSerializer? = null,
    var id: String = "",
    var checkIn: String = "",
    var checkOut: String = "",
    var adults: Int = 0,
    var childrens: Int = 0,
    var rooms: Int = 0,
    var stars:  Int = 0,
    var adults1: String = "",
    var adults2: String = "",
    var adults3: String = "",
    var adults4: String = "",
    var adults5: String = "",
    var adults6: String = "",
    var adults7: String = "",
    var adults8: String = "",
    var children1: String = "",
    var children2: String = "",
    var children3: String = "",
    var children4: String = "",
    var children5: String = "",
    var children6: String = "",
    var children7: String = "",
    var children8: String = "",
    var locale: String = "",
    var currency: String = "",
    var destinationId: Int = 0,
    var pageNumber: String = "1",
    var pageSize: String = "25",
    var sortOrder: String = "PRICE",
    var accommodationsIds: String = "",
    var guestRatingMin: String = "",
    var landmarkIds: String = "",
    var priceMax: String = "",
    var priceMin: String = "",
    var startRatings: String = "",
    var themelds: String = ""
) : Parcelable {
    constructor() : this(null)
}

