package com.hotelurbano.desafio.detailshotels.viewholder

import android.view.View
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.hotelurbano.desafio.R
import com.hotelurbano.desafio.listhotels.model.HotelAmenity

/**
 * Created by Cristian on 17/12/2017.
 */
class AmenityViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun bindItems(item: HotelAmenity) {
        val amenityName = itemView.findViewById(R.id.amenityName) as TextView
        amenityName.text = item.name
    }
}
