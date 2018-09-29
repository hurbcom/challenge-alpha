package br.com.hu.allyson.desafiohu.ui.hotels.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.hu.allyson.desafiohu.R
import br.com.hu.allyson.desafiohu.domain.Hotels
import br.com.hu.allyson.desafiohu.ui.hotels.viewHolder.HotelViewHolder
import br.com.hu.allyson.desafiohu.util.DoubleUtils
import br.com.hu.allyson.desafiohu.util.StatesUtil
import br.com.hu.allyson.desafiohu.util.StringUtils
import com.squareup.picasso.Picasso

class HotelsAdapter(val context: Context, var list: List<Hotels>) : RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.row_hotel, parent, false)
        return HotelViewHolder(context, view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Picasso.get()
            .load(list[position].gallery[0].url)
            .fit()
            .into((holder as HotelViewHolder).hotel_image)

        holder.hotel_location.text = "${list[position].address.city} - ${StatesUtil.getSigleState(list[position].address.state)}"
        holder.name.text = list[position].name
        holder.hotel_includes.text = "Cortesias:\n ${StringUtils.formatAmenities(list[position].amenities)}"
        holder.price.text = DoubleUtils.formatToBrazilianCurrency(list[position].price.amount)

    }
}