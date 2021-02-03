package com.architectcoders.hotelapp.ui

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.SearchView
import androidx.core.view.isVisible
import com.architectcoders.hotelapp.databinding.ActivityMainBinding
import com.architectcoders.hotelapp.model.HotelRetrofit
import com.architectcoders.hotelapp.ui.common.CoroutineScopeActivity
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : CoroutineScopeActivity() {
    private lateinit var binding: ActivityMainBinding

    private val adapter = HotelAdapter {
        binding.searchView.setQuery(it.name, false)
        showSearchResult()
    }

    private fun showSearchResult() {
        hideKeyboard()
        binding.btnSearch.visibility = View.VISIBLE
        binding.gridContainerItemsView.visibility = View.VISIBLE
        binding.recycler.visibility = View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etCheckIn.setOnClickListener {
            hideKeyboard()
            showDatePickerDialog(it as EditText, supportFragmentManager)
        }

        binding.etCheckOut.setOnClickListener {
            hideKeyboard()
            showDatePickerDialog(it as EditText, supportFragmentManager)
        }

        binding.recycler.adapter = adapter
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    setAdapter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (binding.btnSearch.isVisible) {
                    binding.btnSearch.visibility = View.GONE
                    binding.gridContainerItemsView.visibility = View.GONE
                    binding.recycler.visibility = View.VISIBLE
                }
               return false
            }
        })
    }

    fun setAdapter(query: String) {
        launch {
            val destinationResult = HotelRetrofit.service.searchDestination(query, Locale.getDefault().toString())
            if (destinationResult.suggestions[1].entities.isEmpty()) {
                toast("No existen resultados para esa busqueda")
                showSearchResult()
            } else {
                adapter.hotels = destinationResult.suggestions[1].entities
            }
        }

    }
}





