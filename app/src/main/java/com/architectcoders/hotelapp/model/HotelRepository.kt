package com.architectcoders.hotelapp.model

class HotelRepository {

    suspend fun getDetail(selectedSearch: SelectedSearch) =
        HotelRetrofit.service.getDetail(
            id = selectedSearch.id,
            currency = selectedSearch.currency,
            locale = selectedSearch.locale,
            checkIn = selectedSearch.checkIn,
            checkOut = selectedSearch.checkOut,
            adults1 = selectedSearch.adults1
        )

    suspend fun getList(selectedSearch: SelectedSearch) =
        HotelRetrofit.service.listHotels(
            destinationId = selectedSearch.destinationId,
            pageNumber = selectedSearch.pageNumber,
            pageSize = selectedSearch.pageSize,
            currency = selectedSearch.currency,
            sortOrder = selectedSearch.sortOrder,
            locale = selectedSearch.locale,
            checkIn = selectedSearch.checkIn,
            checkOut = selectedSearch.checkOut,
            adults1 = selectedSearch.adults1,
            adults2 = selectedSearch.adults2,
            adults3 = selectedSearch.adults3,
            adults4 = selectedSearch.adults4,
            adults5 = selectedSearch.adults5,
            adults6 = selectedSearch.adults6,
            adults7 = selectedSearch.adults7,
            adults8 = selectedSearch.adults8,
            children1 = selectedSearch.children1,
            children2 = selectedSearch.children2,
            children3 = selectedSearch.children3,
            children4 = selectedSearch.children4,
            children5 = selectedSearch.children5,
            children6 = selectedSearch.children6,
            children7 = selectedSearch.children7,
            children8 = selectedSearch.children8,
            accommodationsIds = selectedSearch.accommodationsIds,
            guestRatingMin = selectedSearch.guestRatingMin,
            landmarkIds = selectedSearch.landmarkIds,
            priceMax = selectedSearch.priceMax,
            priceMin = selectedSearch.priceMin,
            startRatings = selectedSearch.startRatings,
            themelds = selectedSearch.themelds
        ).data.body.searchResults.results
}
