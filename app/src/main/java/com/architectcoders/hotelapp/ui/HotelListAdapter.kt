package com.architectcoders.hotelapp.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.architectcoders.hotelapp.R
import com.architectcoders.hotelapp.databinding.ActivityListItemBinding
import com.architectcoders.hotelapp.model.HotelSerializer
import kotlin.properties.Delegates

class HotelListAdapter(private val listener: (HotelSerializer) -> Unit) : RecyclerView.Adapter<HotelListAdapter.ViewHolder>() {

    var hotels: List<HotelSerializer> by Delegates.observable(emptyList()) { _, old, new ->
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition].id == new[newItemPosition].id

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                old[oldItemPosition] == new[newItemPosition]

            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size
        }).dispatchUpdatesTo(this)
    }

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