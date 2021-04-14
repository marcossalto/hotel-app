package com.architectcoders.hotelapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class SelectedSearch (
    var hotel: HotelSerializer,
    val id: String = "",
    val checkIn: String = "",
    val checkOut: String = "",
    val adults1: String = "",
    val locale: String = "",
    val currency: String = ""
) : Parcelable

