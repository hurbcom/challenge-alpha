package com.ayodkay.alpha.challenge.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ayodkay.alpha.challenge.R
import com.ayodkay.alpha.challenge.adapter.SliderAdapter
import com.ayodkay.alpha.challenge.database.HotelsDataViewModel
import com.ayodkay.alpha.challenge.util.UtilFunctions
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import kotlinx.android.synthetic.main.activity_hotel_details.*


class HotelDetails : AppCompatActivity(),OnMapReadyCallback {

    lateinit var googleMap: GoogleMap
    lateinit var hotelData: HotelsDataViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_details)

        val position = intent.extras?.get("position") as Int

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map_view) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

        hotelData = ViewModelProviders.of(this).get(HotelsDataViewModel::class.java)

        hotelData.allHotels.observe(this, Observer {
            price.text =  "R$ ${it[position].price}"
            full_description.text = it[position].details.descriptions
            first_amenity.text = it[position].amenities[position][0].name

            if (it[position].huFreeCancellation){
                second_amenity.text = "Free Cancellation"
                try {
                    third_amenity.text = it[position].amenities[position][1].name
                }catch (E:Exception){
                    third_amenity.visibility = View.GONE
                }
            }else{

                try {
                    second_amenity.text = it[position].amenities[position][1].name
                    third_amenity.text = it[position].amenities[position][2].name
                }catch (E:Exception){
                    card3.visibility = View.GONE
                    card2.visibility = View.GONE
                    second_amenity.visibility = View.GONE
                    third_amenity.visibility = View.GONE
                }

            }


            imageSlider.apply {
                sliderAdapter = SliderAdapter(it[position].name, it[position].images,position)
                startAutoCycle()
                setIndicatorAnimation(IndicatorAnimations.WORM)
                setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            }

        })
    }

    override fun onMapReady(googleMaps: GoogleMap?) {
        googleMap = googleMaps!!

        // Add a marker in Sydney, Australia, and move the camera.
        val sydney = LatLng(-37.81319, 144.96298)

        googleMap.apply {
            addMarker(MarkerOptions().position(sydney))
            moveCamera(CameraUpdateFactory.newLatLng(sydney))
        }
    }
}
