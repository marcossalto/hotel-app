package com.architectcoders.hotelapp.ui

import android.os.Bundle
import android.widget.SearchView
import com.architectcoders.hotelapp.databinding.ActivityMainBinding
import com.architectcoders.hotelapp.model.HotelRetrofit
import com.architectcoders.hotelapp.ui.common.CoroutineScopeActivity
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : CoroutineScopeActivity() {
    private val adapter = HotelAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.adapter = adapter
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    setAdapter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    fun setAdapter(query: String ){
        launch {
            val destinationResult = HotelRetrofit.service.searchDestination(query, Locale.getDefault().toString())
            adapter.hotels = destinationResult.suggestions[1].entities
        }
    }
}



