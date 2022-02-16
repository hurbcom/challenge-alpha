package com.isranascimento.theme.hotel

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.isranascimento.theme.R
import com.isranascimento.theme.databinding.HotelListCardItemBinding
import com.isranascimento.utils.extensions.getDimension
import com.isranascimento.utils.extensions.load
import com.isranascimento.utils.hotels.createAmenityTextview

class HotelCardItemView(
    context: Context
): ConstraintLayout(context) {
    val binding = HotelListCardItemBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val lp = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        lp.bottomMargin = context.getDimension(R.dimen.spacing_small)
        lp.topMargin = context.getDimension(R.dimen.spacing_small)

        layoutParams = lp
    }

    fun bind(hotel: HotelCardItem) {
        binding.hotelImage.load(hotel.image)
        binding.hotelTitle.text = hotel.name

        binding.amenities.removeAllViews()
        hotel.amenities.forEach {
            binding.amenities.addView(createAmenityTextview(binding.root.context, it))
        }
        binding.location.text =
            binding.root.context.getString(R.string.hotel_list_location_text, hotel.city, hotel.state)
    }

    fun bindCallback(hotel: HotelCardItem, listener: HotelCardItemListener) {
        binding.root.setOnClickListener {
            listener.onHotelClick(hotel.id)
        }
    }

    interface HotelCardItemListener {
        fun onHotelClick(hotelId: String)
    }
}