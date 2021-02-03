package com.architectcoders.hotelapp.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.architectcoders.hotelapp.model.Hotel
import com.architectcoders.hotelapp.R
import com.architectcoders.hotelapp.databinding.CustomListDestinationBinding
import kotlin.properties.Delegates

class HotelAdapter(private val listener: (Hotel) -> Unit) :
        RecyclerView.Adapter<HotelAdapter.ViewHolder>() {

    var hotels: List<Hotel> by Delegates.observable(emptyList()) { _, old, new ->
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    old[oldItemPosition].destinationId == new[newItemPosition].destinationId

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    old[oldItemPosition] == new[newItemPosition]

            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size
        }).dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.custom_list_destination, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = hotels.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hotel = hotels[position]
        holder.bind(hotel)
        holder.itemView.setOnClickListener {
            hotels = emptyList()
            listener(hotel)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CustomListDestinationBinding.bind(view)
        fun bind(hotel: Hotel) {
            binding.tvName.text = hotel.name
        }
    }
}