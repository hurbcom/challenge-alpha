package com.hotelurbano.desafio.detailshotels.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hotelurbano.desafio.R
import com.hotelurbano.desafio.detailshotels.viewholder.AmenityViewHolder
import com.hotelurbano.desafio.listhotels.model.HotelAmenity

/**
 * Created by Cristian on 17/12/2017.
 */
class AmenityAdapter(private val list: List<HotelAmenity>) : RecyclerView.Adapter<AmenityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmenityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_amenities, parent, false)
        return AmenityViewHolder(view)
    }

    override fun onBindViewHolder(holder: AmenityViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}