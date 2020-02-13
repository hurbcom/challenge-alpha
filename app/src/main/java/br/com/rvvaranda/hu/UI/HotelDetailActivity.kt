package br.com.rvvaranda.hu.UI

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.rvvaranda.hu.Model.Hotel
import br.com.rvvaranda.hu.R
import com.denzcoskun.imageslider.models.SlideModel
import com.google.gson.Gson
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import kotlinx.android.synthetic.main.activity_hotel_detail.*

class HotelDetailActivity : AppCompatActivity() {

    var jsonHotel = ""
    private lateinit var hotel: Hotel
    lateinit var result: Drawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_detail)

        toolbar.title = "Detalhes"

        jsonHotel =
            intent.getStringExtra("selectedHotel").let { intent.getStringExtra("selectedHotel") }
                ?: ""
        hotel = Gson().fromJson(jsonHotel, Hotel::class.java)

        fillGalery()
        fillFields()

        configToolbar()
    }

    private fun configToolbar() {


        setSupportActionBar(toolbar)

        result = DrawerBuilder()
            .withActivity(this)
            .withDisplayBelowStatusBar(false)
            .withTranslucentStatusBar(false)
            .build()

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(false)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen) {
            result.closeDrawer()
        } else {
            super.onBackPressed()
        }
    }


    fun fillFields() {

        tvDetailHotelName.text = hotel.name
        tvDetailHotelCity.text = hotel.address.city
        detailRatingBar.rating = hotel.stars.toFloat()
        tvDetailHotelAddress.text = hotel.address.fullAddress
        tvDetailHotelDescription.text = hotel.description

    }

    fun fillGalery() {

        try {

            val imageList = ArrayList<SlideModel>()

            for (img in hotel.gallery) {
                imageList.add(SlideModel(img.url))
            }


            image_slider.setImageList(imageList)

        } catch (ex: Exception) {
            Log.e("Hurb", ex.message)
        }
    }
}
