package com.architectcoders.hotelapp.ui.list

import com.architectcoders.hotelapp.model.HotelRepository
import com.architectcoders.hotelapp.model.HotelSerializer
import com.architectcoders.hotelapp.model.SelectedSearch
import com.architectcoders.hotelapp.ui.common.Scope
import kotlinx.coroutines.launch

class ListPresenter(private val hotelRepository: HotelRepository) : Scope by Scope.Impl() {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun updateData(hotels: List<HotelSerializer>)
        fun navigateTo(selectedSearch: SelectedSearch)
    }

    private var view: View? = null
    private var selectedSearch: SelectedSearch? = null

    fun onCreate(view: View, selectedSearch: SelectedSearch) {
        initScope()
        this.view = view
        this.selectedSearch = selectedSearch
        setGuestsInRooms()

        launch {
            with(view) {
                showProgress()
                updateData(hotelRepository.getList(selectedSearch))
                hideProgress()
            }
        }
    }

    fun onHotelClicked(hotel: HotelSerializer) {
        selectedSearch?.let {
            with(it) {
                it.hotel = hotel
                id = hotel.id.toString()
            }
            view?.navigateTo(it)
        }
    }

    fun onDestroy() {
        this.view = null
        destroyScope()
    }

    private fun setGuestsInRooms() {
        selectedSearch?.let { item ->
            with(item) {
                val r = (this.adults.div(rooms.toDouble()))
                val f = kotlin.math.floor(r).toInt()
                var resto = this.adults

                for (i in 1..rooms) {
                    when (i) {
                        1 -> {
                            f.also {
                                adults1 = if (i == rooms)
                                    (resto).toString()
                                else
                                    it.toString()
                                resto = resto.minus(it)
                            }
                        }
                        2 -> {
                            f.also {
                                adults2 = if (i == rooms)
                                    (resto).toString()
                                else
                                    it.toString()
                                resto = resto.minus(it)
                            }
                        }
                        3 -> {
                            f.also {
                                adults3 = if (i == rooms)
                                    (resto).toString()
                                else
                                    it.toString()
                                resto = resto.minus(it)
                            }
                        }
                        4 -> {
                            f.also {
                                adults4 = if (i == rooms)
                                    (resto).toString()
                                else
                                    it.toString()
                                resto = resto.minus(it)
                            }
                        }
                        5 -> {
                            f.also {
                                adults5 = if (i == rooms)
                                    (resto).toString()
                                else
                                    it.toString()
                                resto = resto.minus(it)
                            }
                        }
                        6 -> {
                            f.also {
                                adults6 = if (i == rooms)
                                    (resto).toString()
                                else
                                    it.toString()
                                resto = resto.minus(it)
                            }
                        }
                        7 -> {
                            f.also {
                                adults7 = if (i == rooms)
                                    (resto).toString()
                                else
                                    it.toString()
                                resto = resto.minus(it)
                            }
                        }
                        8 -> {
                            f.also {
                                adults8 = if (i == rooms)
                                    (resto).toString()
                                else
                                    it.toString()
                                resto = resto.minus(it)
                            }
                        }
                    }
                }
            }
        }
    }
}
