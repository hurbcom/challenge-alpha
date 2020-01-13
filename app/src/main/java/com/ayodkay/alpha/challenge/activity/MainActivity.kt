package com.ayodkay.alpha.challenge.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayodkay.alpha.challenge.R
import com.ayodkay.alpha.challenge.adapter.HotelAdapter
import com.ayodkay.alpha.challenge.util.UtilFunctions
import com.ayodkay.alpha.challenge.viewmodel.HotelViewModel
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    lateinit var hotelModel :HotelViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hotel_recycle.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        hotelModel = ViewModelProviders.of(this).get(HotelViewModel::class.java)

        hotelModel.getHotels().observe(this, Observer {
            val adapter = HotelAdapter(this, UtilFunctions(this).handleJson(it))
            hotel_recycle.adapter = adapter
        })


        chipGroup.setOnCheckedChangeListener { chipGroup, i ->
            val chip: Chip = chipGroup.findViewById(i)
            when(val chipStar = chip.text.toString()){

                "view all"->{
                    hotelModel.getHotels().observe(this, Observer {
                        val adapter = HotelAdapter(this, UtilFunctions(this).handleJson(it))
                        hotel_recycle.adapter = adapter

                    })
                }

                "3"->{
                    hotelModel.getHotels().observe(this, Observer {
                        val adapter = HotelAdapter(this, UtilFunctions(this).handleStarJson(it,chipStar))
                        hotel_recycle.adapter = adapter

                    })
                }

                "4"->{
                    hotelModel.getHotels().observe(this, Observer {
                        val adapter = HotelAdapter(this, UtilFunctions(this).handleStarJson(it,chipStar))
                        hotel_recycle.adapter = adapter

                    })
                }

                "5"->{
                    hotelModel.getHotels().observe(this, Observer {
                        val adapter = HotelAdapter(this, UtilFunctions(this).handleStarJson(it,chipStar))
                        hotel_recycle.adapter = adapter

                    })
                }
            }


        }

    }

}
