package com.filipeoliveira.hurbchallenge.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.filipeoliveira.hurbchallenge.R
import com.filipeoliveira.hurbchallenge.databinding.ItemHotelBinding
import com.filipeoliveira.hurbchallenge.ui.model.HotelUI

class HotelListAdapter(
    val onClick: (HotelUI) -> Unit
) : RecyclerView.Adapter<HotelListAdapter.HotelListViewHolder>() {

    private val hotelList = mutableListOf<HotelUI>()

    inner class HotelListViewHolder(private val binding: ItemHotelBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hotel: HotelUI) {
            binding.root.layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
            binding.hotelUI = hotel

            if (hotel.image.isNotEmpty()) {
                Glide.with(binding.root)
                    .load(hotel.image)
                    .placeholder(R.drawable.ic_hotel)
                    .error(R.drawable.ic_hotel)
                    .into(binding.itemHotelImage)
            }

            val enabledColor = ContextCompat.getColor(binding.root.context, R.color.secondaryColor)
            val disabledColor = ContextCompat.getColor(binding.root.context, R.color.primaryColor)

            val gymColor = if (hotel.hasGymAmenity()) enabledColor else disabledColor
            val barColor = if (hotel.hasBarAmenity()) enabledColor else disabledColor
            val wheelchairColor = if (hotel.hasWheelchairAmenity()) enabledColor else disabledColor
            val wifiColor = if (hotel.hasWifiAmenity()) enabledColor else disabledColor
            val tvColor = if (hotel.hasTvAmenity()) enabledColor else disabledColor
            val toiletColor = if (hotel.hasToiletAmenity()) enabledColor else disabledColor
            val parkingColor = if (hotel.hasParkingAmenity()) enabledColor else disabledColor
            val poolColor = if (hotel.hasPoolAmenity()) enabledColor else disabledColor
            val receptionColor = if (hotel.hasReceptionAmenity()) enabledColor else disabledColor
            val restaurantColor = if (hotel.hasRestaurantAmenity()) enabledColor else disabledColor

            binding.itemHotelGym.setColorFilter(gymColor, android.graphics.PorterDuff.Mode.SRC_IN)
            binding.itemHotelBar.setColorFilter(barColor, android.graphics.PorterDuff.Mode.SRC_IN)
            binding.itemHotelWheelChair.setColorFilter(
                wheelchairColor,
                android.graphics.PorterDuff.Mode.SRC_IN
            )
            binding.itemHotelWifi.setColorFilter(wifiColor, android.graphics.PorterDuff.Mode.SRC_IN)
            binding.itemHotelTv.setColorFilter(tvColor, android.graphics.PorterDuff.Mode.SRC_IN)
            binding.itemHotelToilet.setColorFilter(
                toiletColor,
                android.graphics.PorterDuff.Mode.SRC_IN
            )
            binding.itemHotelParking.setColorFilter(
                parkingColor,
                android.graphics.PorterDuff.Mode.SRC_IN
            )
            binding.itemHotelPool.setColorFilter(poolColor, android.graphics.PorterDuff.Mode.SRC_IN)
            binding.itemHotelReception.setColorFilter(
                receptionColor,
                android.graphics.PorterDuff.Mode.SRC_IN
            )
            binding.itemHotelRestaurant.setColorFilter(
                restaurantColor,
                android.graphics.PorterDuff.Mode.SRC_IN
            )

            binding.root.setOnClickListener {
                onClick(hotel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHotelBinding.inflate(inflater)
        return HotelListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HotelListViewHolder, position: Int) {
        val hotel = hotelList[position]
        holder.bind(hotel)
    }

    override fun getItemCount(): Int {
        return hotelList.size
    }

    fun setData(hotels: List<HotelUI>) {
        hotelList.addAll(hotels)
        notifyDataSetChanged()
    }

    fun clear() {
        hotelList.clear()
        notifyDataSetChanged()
    }
}
