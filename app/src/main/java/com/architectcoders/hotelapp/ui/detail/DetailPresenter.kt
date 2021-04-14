package com.architectcoders.hotelapp.ui.detail

import com.architectcoders.hotelapp.model.HotelDetailRepository
import com.architectcoders.hotelapp.model.HotelDetailSerializer
import com.architectcoders.hotelapp.model.SelectedSearch
import com.architectcoders.hotelapp.ui.common.Scope
import kotlinx.coroutines.launch

class DetailPresenter(private val hotelDetailRepository: HotelDetailRepository) : Scope by Scope.Impl()  {

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
            view.showProgress()
            view.updateData(hotelDetailRepository.getDetail(selectedSearch))
            view.hideProgress()
        }
    }

    fun onDestroy() {
        view = null
        destroyScope()
    }
}
