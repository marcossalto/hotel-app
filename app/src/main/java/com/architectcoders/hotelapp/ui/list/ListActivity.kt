package com.architectcoders.hotelapp.ui.list

import android.os.Bundle
import android.view.View
import com.architectcoders.hotelapp.databinding.ActivityListBinding
import com.architectcoders.hotelapp.model.HotelRepository
import com.architectcoders.hotelapp.model.HotelSerializer
import com.architectcoders.hotelapp.model.SelectedSearch
import com.architectcoders.hotelapp.ui.common.CoroutineScopeActivity
import com.architectcoders.hotelapp.ui.detail.DetailActivity
import com.architectcoders.hotelapp.ui.startActivity

class ListActivity : CoroutineScopeActivity(), ListPresenter.View {
    companion object {
        const val SELECTED_SEARCH = "ListActivity:selectedSearch"
    }

    private val presenter by lazy { ListPresenter(HotelRepository()) }
    private val adapter = HotelListAdapter { presenter.onHotelClicked(it) }
    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val selectedSearch = intent.getParcelableExtra<SelectedSearch>(SELECTED_SEARCH)
            ?: throw IllegalStateException("Hotel not found")

        binding = ActivityListBinding.inflate(layoutInflater).apply {
            setContentView(root)
            presenter.onCreate(this@ListActivity, selectedSearch)
            recycler.adapter = adapter
        }
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgress() {
        binding.progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progress.visibility = View.GONE
    }

    override fun updateData(hotels: List<HotelSerializer>) {
        adapter.hotels = hotels
    }

    override fun navigateTo(selectedSearch: SelectedSearch) {
        startActivity<DetailActivity> {
            putExtra(DetailActivity.HOTEL, selectedSearch)
        }
    }
}
