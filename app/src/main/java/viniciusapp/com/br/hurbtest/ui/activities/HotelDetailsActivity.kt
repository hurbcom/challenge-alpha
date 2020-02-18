package viniciusapp.com.br.hurbtest.ui.activities

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details_hotel.*
import viniciusapp.com.br.hurbtest.R
import viniciusapp.com.br.hurbtest.models.ResultsModel

class HotelDetailsActivity: BaseActivity() {
    lateinit var hotelDetail: ResultsModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_hotel)

        hotelDetail = intent.getSerializableExtra("hotelDetail") as ResultsModel
        sdvImageHotelDetail.setImageURI(hotelDetail.image)

        sdvImageHotelDetail.setOnClickListener {
            val i = Intent(this, GalleryImageHotel::class.java)
            i.putExtra("imageHotel", hotelDetail.gallery)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
        }
    }
}
