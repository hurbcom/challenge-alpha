package com.ayodkay.alpha.challenge.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ayodkay.alpha.challenge.R
import com.ayodkay.alpha.challenge.adapter.SliderAdapter
import com.ayodkay.alpha.challenge.database.HotelsDataViewModel
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import kotlinx.android.synthetic.main.activity_hotel_details.*


class HotelDetails : AppCompatActivity(){

    private lateinit var googleMap: GoogleMap
    private lateinit var hotelData: HotelsDataViewModel
    private lateinit var geoLocation :LatLng

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_details)

        backbt.setOnClickListener {
            onBackPressed()
        }

        //get the position of the item clicked
        val position = intent.extras?.get("position") as Int

        hotelData = ViewModelProviders.of(this).get(HotelsDataViewModel::class.java)

        hotelData.allHotels.observe(this, Observer {hotelData->
            price.text =  "R$ ${hotelData[position].price}"
            full_description.text = hotelData[position].details.descriptions
            location_address.text = hotelData[position].address.address
            first_amenity.text = hotelData[position].amenities[position][0].name

            geoLocation = LatLng(hotelData[position].address.lat,hotelData[position].address.lon)

            Handler().postDelayed({
                map_view.apply {
                    onCreate(Bundle.EMPTY)
                    getMapAsync {
                        googleMap = it
                        googleMap.apply {
                            addMarker(MarkerOptions().position(geoLocation)
                                .title(hotelData[position].name))
                            moveCamera(CameraUpdateFactory.newLatLngZoom(geoLocation,12.0f))
                            uiSettings.apply {
                                isScrollGesturesEnabled = false
                            }
                        }
                    }
                    MapsInitializer.initialize(this@HotelDetails)
                }
            },3000)

            if (hotelData[position].huFreeCancellation){
                second_amenity.text = "Free Cancellation"
                try {
                    third_amenity.text = hotelData[position].amenities[position][1].name
                }catch (E:Exception){
                    card3.visibility = View.GONE
                }
            }else{

                try {
                    second_amenity.text = hotelData[position].amenities[position][1].name
                    third_amenity.text = hotelData[position].amenities[position][2].name
                }catch (E:Exception){
                    card3.visibility = View.GONE
                    card2.visibility = View.GONE
                    second_amenity.visibility = View.GONE
                    third_amenity.visibility = View.GONE
                }

            }
            imageSlider.apply {
                sliderAdapter = SliderAdapter(hotelData[position].name,
                    hotelData[position].images,position)
                startAutoCycle()
                setIndicatorAnimation(IndicatorAnimations.WORM)
                setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            }

        })
    }
}
