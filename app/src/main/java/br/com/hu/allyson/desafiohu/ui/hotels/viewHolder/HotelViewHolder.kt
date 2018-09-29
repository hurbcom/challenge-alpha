package br.com.hu.allyson.desafiohu.ui.hotels.viewHolder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_hotel.view.*



class HotelViewHolder(context: Context, view: View): RecyclerView.ViewHolder(view) {

    var hotel_image = view.hotel_image
    var hotel_location = view.hotel_location
    var name = view.name
    var hotel_includes = view.hotel_includes
    var price = view.price
}