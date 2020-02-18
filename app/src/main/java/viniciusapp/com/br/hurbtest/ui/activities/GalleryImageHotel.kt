package viniciusapp.com.br.hurbtest.ui.activities

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import viniciusapp.com.br.hurbtest.R
import viniciusapp.com.br.hurbtest.models.GalleryModel
import viniciusapp.com.br.hurbtest.ui.adapters.ImageGalleryAdapter

class GalleryImageHotel: BaseActivity() {
    lateinit var hotelGalleryDetail: ArrayList<GalleryModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_image_hotel)

        // recebendo o obj da tela anterior.
        hotelGalleryDetail = intent.getSerializableExtra("imageHotel") as ArrayList<GalleryModel>

        initComponents(hotelGalleryDetail)
    }

    private fun initComponents(gallery: List<GalleryModel>) {
        val recycler = findViewById<RecyclerView>(R.id.rcvImageDetails)
        val sdvImageHotelDetail = findViewById<SimpleDraweeView>(R.id.sdvImageGalleryHotelDetail)
        sdvImageHotelDetail.setImageURI(gallery[0].url)

        recycler.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.HORIZONTAL, false)
        recycler.adapter = ImageGalleryAdapter(gallery, object : ImageGalleryAdapter.Listener{
            override fun onClick(url: String) {
                sdvImageHotelDetail.setImageURI(url)
            }
        })
    }
}