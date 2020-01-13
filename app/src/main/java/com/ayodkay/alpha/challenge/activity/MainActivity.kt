package com.ayodkay.alpha.challenge.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayodkay.alpha.challenge.R
import com.ayodkay.alpha.challenge.adapter.HotelAdapter
import com.ayodkay.alpha.challenge.model.HotelModel
import com.ayodkay.alpha.challenge.viewmodel.HotelViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hotel_recycle.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        val hotelModel = ViewModelProviders.of(this).get(HotelViewModel::class.java)

        hotelModel.getHotels().observe(this, Observer {
            val adapter = HotelAdapter(this, handleJson(it))
            hotel_recycle.adapter = adapter
        })
    }


    private fun handleJson(response: JSONObject): ArrayList<HotelModel> {

        val hotels: ArrayList<HotelModel> = arrayListOf()

        var name: String
        var amenities: String
        val image: ArrayList<ArrayList<String>> = arrayListOf(  )
        var price:String? = null
        var description: String
        var address :String? = null
        var city: String? = null
        var state:String? = null
        var country:String? = null
        var stars: Int
        var smallDescription: String
        var huFreeCancellation: Boolean


        val result = response.getJSONArray("results")

        for (results_loop in 0 until result.length()) {
            val res = result.getJSONObject(results_loop)
            name = res.getString("name")
            description = res.getString("description")
            smallDescription = res.getString("smallDescription")
            stars = res.getInt("stars")
            huFreeCancellation = res.getBoolean("hu_free_cancellation")

            val am = res.getJSONArray("amenities")
            for (amenities_loop in 0 until am.length()) {
                val am_res = am.getJSONObject(amenities_loop)
                val name = am_res.getString("name")
                val category = am_res.getString("category")

                //amenities.add(Amenities(name, category))

            }

            val gallery = res.getJSONArray("gallery")
            val imageList = arrayListOf<String>()
            for (gallery_loop in 0 until gallery.length()) {

                val galleryLoopResult = gallery.getJSONObject(gallery_loop)
                val url  = galleryLoopResult.getString("url")

                imageList.add(url)


            }
            image.add(imageList)
            val pr = res.getJSONObject("price")
            for (price_loop in 0 until pr.length()) {
                price = pr.getString("amount")

            }
            val ad = res.getJSONObject("address")

            for (address_loop in 0 until ad.length()) {
                val zipcode = ad.getString("zipcode")
                address = ad.getString("full_address")
                city = ad.getString("city")
                state = ad.getString("state")
                country = ad.getString("country")

            }

            hotels.add(
                HotelModel(name,price!!,image,smallDescription,address!!,city!!,state!!,country!!,stars,huFreeCancellation)
            )
        }

        return hotels
    }

}
