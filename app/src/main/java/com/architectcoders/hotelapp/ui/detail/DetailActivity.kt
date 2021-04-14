package com.architectcoders.hotelapp.ui.detail

import android.os.Bundle
import com.architectcoders.hotelapp.databinding.ActivityDetailBinding
import com.architectcoders.hotelapp.model.HotelDetailSerializer
import com.architectcoders.hotelapp.model.HotelRetrofit
import com.architectcoders.hotelapp.model.HotelSerializer
import com.architectcoders.hotelapp.ui.common.CoroutineScopeActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.coroutines.launch

class DetailActivity : CoroutineScopeActivity() {
    companion object {
        const val HOTEL = "DetailActivity:hotel"
    }

    private lateinit var hotelDetailSerializaer: HotelDetailSerializer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val hotel = intent.getParcelableExtra<HotelSerializer>(HOTEL) ?: throw IllegalStateException()
        val id: String = hotel.id.toString()
        val checkIn: String =  intent.getStringExtra("checkIn").toString()
        val checkOut: String = intent.getStringExtra("checkOut").toString()
        val adults1: String = intent.getStringExtra("adults1").toString()
        val locale: String = intent.getStringExtra("locale").toString()
        val currency: String = intent.getStringExtra("currency").toString()

        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launch {
           hotelDetailSerializaer = HotelRetrofit.service.getDetail(
               id = id.toInt(),
               currency = currency,
               locale = locale,
               checkIn = checkIn,
               checkOut = checkOut,
               adults1 = adults1
           )
            binding.hotelDetailInfo.setHotel(hotelDetailSerializaer)
        }

        hotel.run {
            binding.tbHotelDetail.title = name
            Glide.with(binding.root.context)
                .load(optimizedThumbUrls.srpDesktop)
                .transition(DrawableTransitionOptions.withCrossFade())
                .transform(CenterCrop())
                .into(binding.ivHotelDetail)
            binding.tvHotelDetailSummary.text = name
        }
    }
}
