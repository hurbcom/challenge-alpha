package com.ayodkay.alpha.challenge.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ayodkay.alpha.challenge.R
import com.ayodkay.alpha.challenge.database.HotelsDataViewModel
import com.ayodkay.alpha.challenge.util.UtilFunctions
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_hotel_details.*


class HotelDetails : AppCompatActivity(),OnMapReadyCallback {

    lateinit var googleMap: GoogleMap
    lateinit var hotelData: HotelsDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_details)

        val hotelName = intent.extras?.get("hotelName") as String

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map_view) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)

        val owner = this as LifecycleOwner

        hotelData = ViewModelProviders.of(this).get(HotelsDataViewModel::class.java)

        val aaaa = UtilFunctions(this).handleDatabase(hotelData,owner,hotelName)


        price.text = aaaa.price
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
