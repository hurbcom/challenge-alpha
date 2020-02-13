package br.com.rvvaranda.hu.Adapter.Holders

import android.content.Intent
import android.graphics.Paint
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.rvvaranda.hu.Adapter.HotelsListAdapter
import br.com.rvvaranda.hu.UI.HotelDetailActivity
import br.com.rvvaranda.hu.Model.Hotel
import br.com.rvvaranda.hu.R
import com.google.gson.Gson
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_hotels_list_adapter.view.*


class HotelsListHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var view: View = itemView
    private var _hotel: Hotel? = null

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        try {
            val intent = Intent(view.context, HotelDetailActivity::class.java)
                .putExtra("selectedHotel", Gson().toJson(_hotel))

            view.context.startActivity(intent)
        } catch (ex: Exception) {
            Log.e("error", ex.message)
        }
    }

    fun bindData(hotel: Hotel) {
        try {

            _hotel = hotel

            view.groupTitle.visibility = View.GONE

            showTitle(hotel.stars)

            view.tvOldPrice.visibility = View.GONE
            view.tvFreeCancel.visibility = View.GONE
            view.ratingBar.visibility =  View.GONE


            view.tvHotelName.text = hotel.name
            view.tvPrice.text = "R$ ${Math.round(hotel.price.amount)}"
            view.tvLocal.text = "${hotel.address.city}  -  ${hotel.address.state}"


            if (hotel.price.amount < hotel.price.oldPrice) {
                view.tvOldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                view.tvOldPrice.text = "R$ ${Math.round(hotel.price.oldPrice)}"
                view.tvOldPrice.visibility = View.VISIBLE
            }

            if (hotel.huFreeCancellation) {
                view.tvFreeCancel.visibility = View.VISIBLE
            }

            if(hotel.stars > 0) {
                view.ratingBar.visibility = View.VISIBLE
                view.ratingBar.rating = hotel.stars.toFloat()
            }

            Picasso.get().load(hotel.image)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.hurb_bg)
                .into(view.imgHotel)

            val amentiesTextViews = arrayOf(view.amenie1, view.amenie2, view.amenie3)

            for ((index, amentie) in hotel.amenities.take(3).withIndex()) {
                amentiesTextViews[index].text = amentie.name
            }

        } catch (ex: Exception) {
            Log.e("error", ex.message)
        }
    }

    private fun showTitle(stars: Int) {

        if(HotelsListAdapter.currentStar != stars) {
            if (HotelsListAdapter.currentStar == 0) {
                HotelsListAdapter.currentStar = stars
                view.groupTitle.text = if (stars > 0) "$stars Estrelas" else "Pacotes"
                view.groupTitle.visibility = View.VISIBLE
            } else {
                if (HotelsListAdapter.currentStar != stars) {
                    HotelsListAdapter.currentStar = stars
                    view.groupTitle.text = if (stars > 0) "$stars Estrelas" else "Pacotes"
                    view.groupTitle.visibility = View.VISIBLE
                }
            }
        }

    }
}
