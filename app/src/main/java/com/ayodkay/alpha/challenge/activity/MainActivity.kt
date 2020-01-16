package com.ayodkay.alpha.challenge.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayodkay.alpha.challenge.R
import com.ayodkay.alpha.challenge.adapter.HotelAdapter
import com.ayodkay.alpha.challenge.database.HotelsDataViewModel
import com.ayodkay.alpha.challenge.util.UtilFunctions
import com.ayodkay.alpha.challenge.viewmodel.HotelViewModel
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var hotelModel: HotelViewModel
    private lateinit var hotelData: HotelsDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hotel_recycle.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        hotelModel = ViewModelProviders.of(this).get(HotelViewModel::class.java)
        hotelData = ViewModelProviders.of(this).get(HotelsDataViewModel::class.java)


        hotelModel.getHotels().observe(this, Observer {hotels->
            // update UI
            val adapter = HotelAdapter(this, UtilFunctions().handleJson(hotels))
            hotelData.insert(UtilFunctions().handleJsonToData(hotels))
            loading.visibility = View.GONE
            hotel_recycle.adapter = adapter
        })


        chipGroup.setOnCheckedChangeListener { chipGroup, i ->
            val chip: Chip = chipGroup.findViewById(i)
            when (val chipStar = chip.text.toString()) {

                "view all" -> {
                    hotelModel.getHotels().observe(this, Observer {hotels->
                        val adapter = HotelAdapter(this,
                            UtilFunctions().handleJson(hotels))
                        hotelData.insert(UtilFunctions().handleJsonToData(hotels))
                        hotel_recycle.adapter = adapter

                    })
                }

                "3" ->
                {
                    makeStarRequest(chipStar)
                }

                "4" ->
                {
                    makeStarRequest(chipStar)
                }

                "5" ->
                {
                    makeStarRequest(chipStar)
                }
            }


        }

    }


    private fun makeStarRequest(chipStar: String){
        hotelModel.getHotels().observe(this, Observer {hotels->
            val adapter = HotelAdapter(this, UtilFunctions().handleStarJson(hotels, chipStar))
            hotelData.insert(UtilFunctions().handleJsonToData(hotels))
            hotel_recycle.adapter = adapter

        })
    }
}
