package com.architectcoders.hotelapp.model

class HotelDetailRepository {

    suspend fun getDetail(selectedSearch: SelectedSearch) =
         HotelRetrofit.service.getDetail(
            id = selectedSearch.hotel.id,
            currency = selectedSearch.currency,
            locale = selectedSearch.locale,
            checkIn = selectedSearch.checkIn,
            checkOut = selectedSearch.checkOut,
            adults1 = selectedSearch.adults1
        )
}
