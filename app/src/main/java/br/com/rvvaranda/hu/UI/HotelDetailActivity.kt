package br.com.rvvaranda.hu.UI

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import br.com.rvvaranda.hu.Model.Hotel
import br.com.rvvaranda.hu.R
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_hotel_detail.*
import pereira.agnaldo.previewimgcol.ImageCollectionView

class HotelDetailActivity : AppCompatActivity() {

    var jsonHotel = ""
    private lateinit var hotel: Hotel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_detail)

        jsonHotel = intent.getStringExtra("selectedHotel").let { intent.getStringExtra("selectedHotel") } ?: ""
        hotel = Gson().fromJson(jsonHotel, Hotel::class.java)

        fillGalery()
    }

    fun fillFields(){


    }

    fun fillGalery()
    {
        val collectionView = imageCollectionView

        collectionView.maxRows = ImageCollectionView.NO_ROW_LIMITS
        collectionView.maxRows = 10

        collectionView.maxImagePerRow =3

        collectionView.imageMargin = 10

        collectionView.baseImageHeight = 150

        collectionView.mBackgroundColor = Color.WHITE

        collectionView.pinchToZoom = true

        collectionView.showExternalBorderMargins = true


        collectionView.setOnMoreClicked(object : ImageCollectionView.OnMoreClickListener {
            override fun onMoreClicked(bitmaps: List<Bitmap>) {
                Toast.makeText(collectionView.context, "on mode clicked ", Toast.LENGTH_LONG)
                    .show()
            }
        })


        for(img in hotel.gallery){
            collectionView.addImage(Picasso.get().load(img.url).get())
        }
    }


}
