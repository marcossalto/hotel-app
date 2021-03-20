package com.architectcoders.hotelapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.architectcoders.hotelapp.R
import com.architectcoders.hotelapp.model.Country
import com.bumptech.glide.Glide

class CountrySpinnerAdapter (private val context: Context, var dataSource: List<Country>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.custom_spinner_country, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        vh.label.text = dataSource[position].name.capitalizeWords

        val isoNameCountry = dataSource[position].hcomLocale.substringAfter("_")
        val urlCountry = "https://www.countryflags.io/$isoNameCountry/flat/64.png"

        Glide
            .with(context)
            .load(urlCountry)
            .fitCenter()
            .into(vh.img);

        return view
    }

    override fun getItem(position: Int): Any? {
        return dataSource[position];
    }

    override fun getCount(): Int {
        return dataSource.size;
    }

    override fun getItemId(position: Int): Long {
        return position.toLong();
    }

    private class ItemHolder(row: View?) {
        val label: TextView = row?.findViewById(R.id.text) as TextView
        val img: ImageView = row?.findViewById(R.id.img) as ImageView
    }

}