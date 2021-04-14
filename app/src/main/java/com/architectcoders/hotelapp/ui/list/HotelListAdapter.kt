package com.architectcoders.hotelapp.ui.list

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.architectcoders.hotelapp.R
import com.architectcoders.hotelapp.databinding.ActivityListItemBinding
import com.architectcoders.hotelapp.model.HotelSerializer
import com.architectcoders.hotelapp.ui.basicDiffUtil
import com.architectcoders.hotelapp.ui.inflate
import com.architectcoders.hotelapp.ui.loadUrl

class HotelListAdapter(private val listener: (HotelSerializer) -> Unit) : RecyclerView.Adapter<HotelListAdapter.ViewHolder>() {
    var hotels: List<HotelSerializer> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.activity_list_item, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = hotels.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hotel = hotels[position]
        holder.bind(hotel)
        holder.itemView.setOnClickListener {
            listener(hotel)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ActivityListItemBinding.bind(view)
        fun bind(hotel: HotelSerializer) {
            with (binding){
                ivThumbnail.loadUrl(hotel.optimizedThumbUrls.srpDesktop)
                tvName.text = hotel.name
                tvStreetAddress.text = hotel.address.streetAddress
                ratingBar.rating = hotel.starRating.toFloat()
                with(hotel.landmarks.first()){
                    tvLandmark.text = label
                    tvDistance.text = distance
                }
                tvPrice.text = hotel.ratePlan?.price?.current
            }
        }
    }
}