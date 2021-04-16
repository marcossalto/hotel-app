package com.architectcoders.hotelapp.ui.detail

import com.architectcoders.hotelapp.model.HotelRepository
import com.architectcoders.hotelapp.model.HotelDetailSerializer
import com.architectcoders.hotelapp.model.SelectedSearch
import com.architectcoders.hotelapp.ui.common.Scope
import kotlinx.coroutines.launch

class DetailPresenter(private val hotelRepository: HotelRepository) : Scope by Scope.Impl()  {
    private var view: View? = null

    interface View {
        fun updateData(hotelDetailSerializer: HotelDetailSerializer)
        fun showProgress()
        fun hideProgress()
    }

    fun onCreate(view: View, selectedSearch: SelectedSearch) {
        initScope()
        this.view = view
        launch {
            with(view) {
                showProgress()
                updateData(hotelRepository.getDetail(selectedSearch))
                hideProgress()
            }

        }
    }

    fun onDestroy() {
        view = null
        destroyScope()
    }
}
