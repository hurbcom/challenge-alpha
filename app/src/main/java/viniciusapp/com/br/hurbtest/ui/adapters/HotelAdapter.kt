package viniciusapp.com.br.hurbtest.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import viniciusapp.com.br.hurbtest.R
import viniciusapp.com.br.hurbtest.models.ResultsModel
import viniciusapp.com.br.hurbtest.ui.activities.HotelDetailsActivity
import viniciusapp.com.br.hurbtest.utils.MonetaryFormatter

class HotelAdapter(private var context: Context, private val items: List<ResultsModel>) : RecyclerView.Adapter<HotelAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_hotels, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(context, items[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(context: Context, item: ResultsModel) {
            val cdvListHotel = itemView.findViewById<CardView>(R.id.cdvListHotel)
            val sdvImageHotel = itemView.findViewById<SimpleDraweeView>(R.id.sdvImageHotel)
            val txtNameHotel = itemView.findViewById<TextView>(R.id.txtNameHotel)
            val txtPriceHotel = itemView.findViewById<TextView>(R.id.txtPriceHotel)
            val txtCityHotel = itemView.findViewById<TextView>(R.id.txtCityHotel)
            val txtStateHotel = itemView.findViewById<TextView>(R.id.txtStateHotel)
            val txtAmenitiesHotel = itemView.findViewById<TextView>(R.id.txtAmenitiesHotel)
            val txtStarHotel = itemView.findViewById<TextView>(R.id.txtStarHotel)
            val txtFreeCancellation = itemView.findViewById<TextView>(R.id.txtHuFreeCancellationHotel)

            // elvis operator caso nao tenha dados no objeto.
            txtNameHotel.text = item.nameHotel ?: "No Name"
            txtPriceHotel.text = MonetaryFormatter.format(item.price?.amountPerDay) ?: "No Price"
            txtCityHotel.text = item.address?.city ?: "No City"
            txtStateHotel.text = item.address?.state ?: "No State"
            txtStarHotel.text = item.stars ?: "No Stars"

            if(item.hu_free_cancellation == true) {
                txtFreeCancellation.visibility = View.VISIBLE
            }

            // carregando/exibindo a imagem do hotel.
            val uriPath = item.image
            sdvImageHotel.setImageURI(uriPath)

            // condição para trazer somente 3 amenidades na tela inicial.
            if(!item.amenities.isNullOrEmpty()) {
                val sbAmenities: StringBuilder = StringBuilder()
                for((index, amenity) in item.amenities!!.withIndex()) {
                    sbAmenities.append(amenity.name)
                    if(index >= 2) {
                        break
                    }
                    if(item.amenities!!.size -1 > index) {
                        sbAmenities.append(", ")
                    }
                }
                txtAmenitiesHotel.text = sbAmenities.toString()
            }

            // evento de click para a tela de detalhes, com o objeto sendo passado via Intent.
            cdvListHotel.setOnClickListener {
                val i = Intent(context, HotelDetailsActivity::class.java)
                i.putExtra("hotelDetail", item)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)
            }
        }
    }
}