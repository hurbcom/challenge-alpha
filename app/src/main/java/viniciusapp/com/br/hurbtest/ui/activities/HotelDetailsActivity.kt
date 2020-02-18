package viniciusapp.com.br.hurbtest.ui.activities

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details_hotel.*
import viniciusapp.com.br.hurbtest.R
import viniciusapp.com.br.hurbtest.models.ResultsModel
import viniciusapp.com.br.hurbtest.utils.MonetaryFormatter

class HotelDetailsActivity: BaseActivity() {
    lateinit var hotelDetail: ResultsModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_hotel)

        // recebendo o objeto da tela anterior.
        hotelDetail = intent.getSerializableExtra("hotelDetail") as ResultsModel

        sdvImageHotelDetail.setImageURI(hotelDetail.image)

        // elvis operator novamente, para caso nao tenha dados.
        txtCityHotelDetail.text = hotelDetail.address?.city ?: "Cidade não informada"
        txtNameHotelDetail.text = hotelDetail.nameHotel ?: "Hotel não informado"
        txtDescriptionHotelDetail.text = hotelDetail.smallDescription ?: "Descrição não informada"
        txtPriceHotelDetail.text = MonetaryFormatter.format(hotelDetail.price?.amountPerDay) ?: "Preço não informado"

        // condição para exibir todas as amenidades que tiver no hotel.
        if(!hotelDetail.amenities.isNullOrEmpty()) {
            val sbAmenities: StringBuilder = StringBuilder()
            for((index, amenity) in hotelDetail.amenities!!.withIndex()) {
                sbAmenities.append(amenity.name)

                if(hotelDetail.amenities!!.size -1 > index) {
                    sbAmenities.append(", ")
                }
            }
            txtAmenitiesHotelDetail.text = "Comodidades: ${sbAmenities}"
        }

        // evento do click para a tela de galeria de imagens do hotel, passando tbm o objeto via Intent.
        sdvImageHotelDetail.setOnClickListener {
            val i = Intent(this, GalleryImageHotel::class.java)
            i.putExtra("imageHotel", hotelDetail.gallery)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
        }
    }
}
