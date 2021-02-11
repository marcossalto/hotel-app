package com.architectcoders.hotelapp.ui

import android.R.layout.*
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.architectcoders.hotelapp.R.string.*
import com.architectcoders.hotelapp.databinding.ActivityMainBinding
import com.architectcoders.hotelapp.model.HotelRetrofit
import com.architectcoders.hotelapp.ui.common.CoroutineScopeActivity
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : CoroutineScopeActivity() {
    private lateinit var binding: ActivityMainBinding

    private val rooms = arrayOf("1", "2", "3", "4", "5", "6", "7", "8")
    private val rankings = arrayOf("1", "2", "3", "4", "5")
    private val maxRommies = 9
    private val simpleDateFormat = SimpleDateFormat("d/M/yyyy", Locale.getDefault())
    private val todayString = simpleDateFormat.format(Date())

    private val adapter = HotelAdapter {
        binding.searchView.setQuery(it.name, false)
        hideKeyboard()
        toggleViewComponents(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recycler.adapter = adapter
        binding.etCheckIn.setText(todayString)
        binding.etCheckOut.setText(todayString)

        initializeSearchView()

        listenerEtCheckIn()
        listenerEtCheckout()

        initializeRoomSpinner()
        initializeRankingSpinner()

        textChangeEtAdult()
        textChangeEtChild()
    }

    private fun textChangeEtChild() {
        binding.etChild.addTextChangedListener {
            if (!it.isNullOrEmpty()) {
                with(binding.etAdult.text) {
                    val childSelected = it.toString().toInt()
                    val adultSelected = if (this.isEmpty()) 0 else this.toString().toInt()

                    if (childSelected > maxRommies * rooms.count()) {
                        toast(message_max_child)
                    } else {
                        validateNumberOfRooms(adultSelected, childSelected)
                    }
                }
            }
        }
    }

    private fun textChangeEtAdult() {
        binding.etAdult.addTextChangedListener {
            if (!it.isNullOrEmpty()) {
                with(binding.etChild.text) {
                    val adultSelected = it.toString().toInt()
                    val childSelected = if (this.isEmpty()) 0 else this.toString().toInt()

                    if (adultSelected > maxRommies * rooms.count()) {
                        toast(message_max_adults)
                    } else {
                        validateNumberOfRooms(adultSelected, childSelected)
                    }
                }
            }
        }
    }

    private fun validateNumberOfRooms(adultSelected: Int, childSelected: Int) {
        val roomSelected = binding.spRoom.selectedItem.toString().toInt()
        val resultRoomsAdult = getNumberOfRooms(adultSelected, roomSelected)
        val resultRoomsChild = getNumberOfRooms(childSelected, roomSelected)

        with(binding.spRoom) {
            if (resultRoomsAdult >= resultRoomsChild) {
                setSelection(resultRoomsAdult - 1)
            } else {
                setSelection(resultRoomsChild - 1)
            }
        }
    }

    private fun getNumberOfRooms(peopleSelected: Int, roomSelected: Int): Int {
        // Calcula si se excedio del maximo por cuarto
        return if (peopleSelected.toFloat() / roomSelected > maxRommies) {
            if (peopleSelected.mod9()) peopleSelected / maxRommies else (peopleSelected / maxRommies) + 1
        } else if (peopleSelected < roomSelected) {
            peopleSelected
        } else {
            roomSelected
        }
    }

    private fun listenerEtCheckIn() {
        binding.etCheckIn.setOnClickListener { etView ->
            val etCheckInInter = etView as EditText
            hideKeyboard()
            showDatePickerDialog(supportFragmentManager) {
                if (isEqualOrGreaterThan(it)) {
                    etCheckInInter.setText(it)
                } else {
                    etCheckInInter.cleanAndShowError(message_min_checkin)
                }
            }
        }
    }

    private fun listenerEtCheckout() {
        binding.etCheckOut.setOnClickListener { etView ->
            val etCheckOutInter = etView as EditText
            hideKeyboard()
            showDatePickerDialog(supportFragmentManager) {
                with(binding.etCheckIn) {
                    if (this.text.isEmpty()) {
                        etCheckOutInter.cleanAndShowError(message_empty_checkin)
                    } else {
                        val checkInDateString = this.toString()

                        if (isEqualOrGreaterThan(it, checkInDateString)) {
                            etCheckOutInter.setText(it)
                        } else {
                            etCheckOutInter.cleanAndShowError(message_min_checkout)
                        }
                    }
                }
            }
        }
    }

    private fun initializeRankingSpinner() {
        val adapterRankingSpinner = ArrayAdapter(this, simple_spinner_item, rankings)
        adapterRankingSpinner.setDropDownViewResource(simple_spinner_dropdown_item)
        with(binding.spRanking) {
            adapter = adapterRankingSpinner
            setSelection(0, false)
            gravity = Gravity.CENTER
        }
    }

    private fun initializeRoomSpinner() {
        val adapterRoomSpinner = ArrayAdapter(this, simple_spinner_item, rooms)
        adapterRoomSpinner.setDropDownViewResource(simple_spinner_dropdown_item)
        with(binding.spRoom) {
            adapter = adapterRoomSpinner
            setSelection(0, false)
            gravity = Gravity.CENTER
        }
    }

    private fun initializeSearchView() {
        with(binding.searchView) {
            setOnCloseListener {
                toggleViewComponents(loading = false)
                return@setOnCloseListener false
            }
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        setAdapter(query)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (binding.btnSearch.isVisible) {
                        toggleViewComponents(true)
                    }
                    return false
                }
            })
        }
    }

    private fun setAdapter(query: String) {
        launch {
            binding.progressBar.visibility = View.VISIBLE
            val destinationResult = HotelRetrofit.service.searchDestination(
                query,
                Locale.getDefault().toString()
            )
            binding.progressBar.visibility = View.GONE
            if (destinationResult.suggestions[1].entities.isEmpty()) {
                toast(message_without_results)
                hideKeyboard()
                toggleViewComponents(false)
            } else {
                adapter.hotels = destinationResult.suggestions[1].entities
            }
        }
    }

    private fun isEqualOrGreaterThan(selectDateString: String, compareDateString: String = todayString): Boolean {
        val formatDate = simpleDateFormat
        val selectDate = formatDate.parse(selectDateString)
        val compareDate = formatDate.parse(compareDateString)

        return if (selectDate != null && compareDate != null) {
            selectDate.after(compareDate) || selectDate == compareDate
        } else {
            false
        }
    }

    private fun EditText.cleanAndShowError(message: Int) {
        this.setText("")
        toast(message)
    }

    private fun toggleViewComponents(loading: Boolean) {
        with(binding) {
            btnSearch.visibility = if (loading) View.GONE else View.VISIBLE
            gridContainerItemsView.visibility = if (loading) View.GONE else View.VISIBLE
            recycler.visibility = if (loading) View.VISIBLE else View.GONE
        }
    }
}





