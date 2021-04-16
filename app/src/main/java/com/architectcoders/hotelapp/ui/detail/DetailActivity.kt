package com.architectcoders.hotelapp.ui.detail

import android.os.Bundle
import android.view.View
import com.architectcoders.hotelapp.databinding.ActivityDetailBinding
import com.architectcoders.hotelapp.model.*
import com.architectcoders.hotelapp.ui.common.CoroutineScopeActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class DetailActivity : CoroutineScopeActivity(), DetailPresenter.View {
    companion object {
        const val HOTEL = "DetailActivity:hotel"
    }

    private val presenter by lazy { DetailPresenter(HotelRepository()) }
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val selectedSearch = intent.getParcelableExtra<SelectedSearch>(HOTEL)
            ?: throw IllegalStateException("Hotel not found")

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateHeader(selectedSearch)
        presenter.onCreate(this@DetailActivity, selectedSearch)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun updateData(hotelDetailSerializer: HotelDetailSerializer) {
        binding.hotelDetailInfo.setHotel(hotelDetailSerializer)
    }

    override fun showProgress() {
        binding.progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progress.visibility = View.GONE
    }

    private fun updateHeader(selectedSearch: SelectedSearch) {
        with(binding) {
            tbHotelDetail.title = selectedSearch.hotel?.name
            Glide.with(root.context)
                .load(selectedSearch.hotel?.optimizedThumbUrls?.srpDesktop)
                .transition(DrawableTransitionOptions.withCrossFade())
                .transform(CenterCrop())
                .into(ivHotelDetail)
            tvHotelDetailSummary.text = selectedSearch.hotel?.name
        }
    }
}
