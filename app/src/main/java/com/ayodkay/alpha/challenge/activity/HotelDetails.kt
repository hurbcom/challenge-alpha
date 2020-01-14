package com.ayodkay.alpha.challenge.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ayodkay.alpha.challenge.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class HotelDetails : AppCompatActivity(),OnMapReadyCallback {

    lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_details)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map_view) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
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
