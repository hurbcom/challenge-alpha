package com.filipeoliveira.hurbchallenge.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.filipeoliveira.hurbchallenge.databinding.ItemHotelBinding
import com.filipeoliveira.hurbchallenge.ui.model.HotelUI

class HotelListAdapter() : RecyclerView.Adapter<HotelListAdapter.HotelListViewHolder>() {

    private val hotelList = mutableListOf<HotelUI>()

    inner class HotelListViewHolder(private val binding: ItemHotelBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(hotel: HotelUI){
            binding.root.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)

            if (hotel.image.isNotEmpty()){
                Glide.with(binding.root)
                    .load(hotel.image)
                    .into(binding.itemHotelImage)
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
}
