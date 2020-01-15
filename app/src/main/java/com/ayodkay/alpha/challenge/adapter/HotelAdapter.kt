package com.ayodkay.alpha.challenge.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ayodkay.alpha.challenge.R
import com.ayodkay.alpha.challenge.activity.HotelDetails
import com.ayodkay.alpha.challenge.model.HotelModel
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import com.smarteist.autoimageslider.SliderViewAdapter

class HotelAdapter internal constructor(
    private val context: Context,private val hotels:ArrayList<HotelModel>):
    RecyclerView.Adapter<HotelAdapter.HotelsViewHolder>() {

    inner class HotelsViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var hotelImage: SliderView = itemView.findViewById(R.id.imageSlider)
        var hotelLocation: TextView = itemView.findViewById(R.id.hotel_location)
        var hotelPrice: TextView = itemView.findViewById(R.id.price)
        var smallDescription: TextView = itemView.findViewById(R.id.small_description)
        var star: TextView = itemView.findViewById(R.id.star)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelsViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.hotel_card, parent, false)
        return HotelsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hotels.size
    }

    override fun onBindViewHolder(holder: HotelsViewHolder, position: Int) {

        val hotels = hotels[position]

        holder.hotelImage.apply {
            sliderAdapter = SliderAdapter(hotels.name, hotels.images,position)
            startAutoCycle()
            setIndicatorAnimation(IndicatorAnimations.WORM)
            setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        }


        holder.hotelPrice.text = "R$ ${hotels.price}"
        holder.star.text = hotels.star
        holder.hotelLocation.text = hotels.address.city
        holder.smallDescription.text = hotels.descriptions.smallDescriptions



        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context,HotelDetails::class.java).apply {
                putExtra("position",position)
            })
        }

    }
}