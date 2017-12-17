package com.hotelurbano.desafio.listhotels.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.hotelurbano.desafio.R

/**
 * Created by Cristian on 17/12/2017.
 */
class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val headerSection: TextView = view.findViewById(R.id.headerSection) as TextView
}