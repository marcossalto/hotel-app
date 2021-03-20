package com.architectcoders.hotelapp.ui

import android.os.Bundle
import android.view.View
import com.architectcoders.hotelapp.databinding.ActivityListBinding
import com.architectcoders.hotelapp.model.HotelRetrofit
import com.architectcoders.hotelapp.ui.common.CoroutineScopeActivity
import kotlinx.coroutines.launch

class ListActivity : CoroutineScopeActivity() {
    private val adapter = HotelListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val destinationId: String = intent.getStringExtra("destinationId").toString()
        val checkIn: String = intent.getStringExtra("checkIn").toString().toISODate()
        val checkOut: String = intent.getStringExtra("checkOut").toString().toISODate()
        var adults: Int = intent.getIntExtra("adults", 0)
        val rooms: Int = intent.getIntExtra("rooms", 1)

        var adults1 = ""
        var adults2 = ""
        var adults3 = ""
        var adults4 = ""
        var adults5 = ""
        var adults6 = ""
        var adults7 = ""
        var adults8 = ""

        val r = (adults / rooms.toDouble())
        var f = kotlin.math.floor(r).toInt()
        val c = kotlin.math.ceil(r).toInt()
        var resto = adults

        for(i in 1..rooms){
            when(i){
                1 -> {
                    f.also {
                        if (i == rooms)
                            adults1 = (resto).toString()
                        else
                            adults1 = it.toString()
                        resto -= it
                    }
                }
                2 -> {
                    f.also {
                        if (i == rooms)
                            adults2 = (resto).toString()
                        else
                            adults2 = it.toString()
                        resto -= it
                    }
                }
                3 -> {
                    f.also {
                        if (i == rooms)
                            adults3 = (resto).toString()
                        else
                            adults3 = it.toString()
                        resto -= it
                    }
                }
                4 -> {
                    f.also {
                        if (i == rooms)
                            adults4 = (resto).toString()
                        else
                            adults4 = it.toString()
                        resto -= it
                    }
                }
                5 -> {
                    f.also {
                        if (i == rooms)
                            adults5 = (resto).toString()
                        else
                            adults5 = it.toString()
                        resto -= it
                    }
                }
                6 -> {
                    f.also {
                        if (i == rooms)
                            adults6 = (resto).toString()
                        else
                            adults6 = it.toString()
                        resto -= it
                    }
                }
                7 -> {
                    f.also {
                        if (i == rooms)
                            adults7 = (resto).toString()
                        else
                            adults7 = it.toString()
                        resto -= it
                    }
                }
                8 -> {
                    f.also {
                        if (i == rooms)
                            adults8 = (resto).toString()
                        else
                            adults8 = it.toString()
                        resto -= it
                    }
                }
            }
        }

        var children1 = ""
        var children2 = ""
        var children3 = ""
        var children4 = ""
        var children5 = ""
        var children6 = ""
        var children7 = ""
        var children8 = ""
        val stars: Int = intent.getIntExtra("stars", 1)
        val priceMax: Int = intent.getIntExtra("priceMax", 0)
        val locale: String = intent.getStringExtra("locale").toString()
        val currency = "USD"

        with(ActivityListBinding.inflate(layoutInflater)) {
            setContentView(root)
            recycler.adapter = adapter

            launch {
                progress.visibility = View.VISIBLE
                adapter.hotels = HotelRetrofit.service.listHotels(
                    destinationId = destinationId.toInt(),
                    pageNumber = "1",
                    pageSize = "25",
                    currency = currency,
                    sortOrder = "PRICE",
                    locale = locale,
                    checkIn = checkIn,
                    checkOut = checkOut,
                    adults1 = adults1,
                    adults2 = adults2,
                    adults3 = adults3,
                    adults4 = adults4,
                    adults5 = adults5,
                    adults6 = adults6,
                    adults7 = adults7,
                    adults8 = adults8,
                    children1 = children1,
                    children2 = children2,
                    children3 = children3,
                    children4 = children4,
                    children5 = children5,
                    children6 = children6,
                    children7 = children7,
                    children8 = children8,
                    accommodationsIds = "",
                    guestRatingMin = "",
                    landmarkIds = "",
                    priceMax = "",
                    priceMin = priceMax.toString(),
                    startRatings = stars.toString(),
                    themelds = ""
                ).data.body.searchResults.results
                progress.visibility = View.GONE
            }
        }
    }
}
