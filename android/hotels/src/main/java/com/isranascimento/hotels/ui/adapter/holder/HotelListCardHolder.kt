package com.isranascimento.hotels.ui.adapter.holder

import com.isranascimento.hotels.R
import com.isranascimento.hotels.databinding.HotelListCardItemBinding
import com.isranascimento.hotels.ui.models.HotelListUI
import com.isranascimento.hotels.ui.models.HotelListUIItem
import com.isranascimento.hotels.ui.util.createAmenityTextview
import com.isranascimento.utils.extensions.load

class HotelListCardHolder(
    private val binding: HotelListCardItemBinding,
): BaseHotelListHolder(binding.root) {
    private var onHotelClick: ((sku: String) -> Unit)? = null

    override fun bind(item: HotelListUI) {
        val hotelCardItem = (item as HotelListUIItem).card
        binding.hotelImage.load(hotelCardItem.image)
        binding.hotelTitle.text = hotelCardItem.name

        binding.amenities.removeAllViews()
        hotelCardItem.amenities.forEach {
            binding.amenities.addView(createAmenityTextview(binding.root.context, it))
        }
        binding.location.text =
            binding.root.context.getString(R.string.hotel_list_location_text, hotelCardItem.city, hotelCardItem.state)
        binding.root.setOnClickListener {
            onHotelClick?.invoke(hotelCardItem.id)
        }
    }

    fun setClickListener(callback: (String) -> Unit) {
        onHotelClick = callback
    }
}