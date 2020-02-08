package br.com.rvvaranda.hu.Adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.rvvaranda.hu.HotelDetailActivity
import br.com.rvvaranda.hu.Model.Hotel
import br.com.rvvaranda.hu.R
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_hotels_list_adapter.view.*


class HotelsListAdapter(
    var lstHotels: ArrayList<Hotel>) :
    RecyclerView.Adapter<HotelsListAdapter.HotelsListHolder>() {


    companion object {
        var currentStar = 0
    }

    override fun getItemCount(): Int {
        return lstHotels.count()
    }

    override fun onBindViewHolder(holder: HotelsListHolder, position: Int) {
        val local = lstHotels.get(position)
        holder.bindData(local)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelsListHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_hotels_list_adapter,
            parent,
            false
        ) as View


        return HotelsListHolder(itemView)
    }

    fun addItens(lstNewItens: ArrayList<Hotel>) {
        lstHotels.addAll(lstNewItens)
        notifyDataSetChanged()
    }

    fun clearData() {
        lstHotels.clear()
        currentStar = 0
        notifyDataSetChanged()
    }

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
                view.ratingBar.visibility = View.GONE


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
            if (stars > 0) {
                if (currentStar == 0) {
                    currentStar = stars
                    view.groupTitle.text = "$stars Estrelas"
                    view.groupTitle.visibility = View.VISIBLE
                } else {
                    if (currentStar != stars) {
                        currentStar = stars
                        view.groupTitle.text = "$stars Estrelas"
                        view.groupTitle.visibility = View.VISIBLE
                    }
                }
            } else {
                if (view.groupTitle.visibility == View.GONE) {
                    currentStar = 0
                    view.groupTitle.text = "Pacotes"
                    view.groupTitle.visibility = View.VISIBLE
                }
            }
        }
    }
}