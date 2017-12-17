package com.hotelurbano.desafio.listhotels.viewholder

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import com.hotelurbano.desafio.R

/**
 * Created by Cristian on 17/12/2017.
 */
class ItemViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {

    val hotelGallery: SimpleDraweeView = rootView.findViewById(R.id.hotelGallery) as SimpleDraweeView
    val hotelName: TextView = rootView.findViewById(R.id.hotelName) as TextView
    val hotelDescription: TextView = rootView.findViewById(R.id.hotelDescription) as TextView
    val hotelRoot: CardView = rootView.findViewById(R.id.hotelRoot) as CardView
}