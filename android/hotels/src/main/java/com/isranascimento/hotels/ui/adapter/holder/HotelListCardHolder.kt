package com.isranascimento.hotels.ui.adapter.holder

import android.widget.LinearLayout
import android.widget.TextView
import com.isranascimento.hotels.R
import com.isranascimento.hotels.databinding.HotelListCardItemBinding
import com.isranascimento.hotels.ui.models.HotelListUI
import com.isranascimento.hotels.ui.models.HotelListUIItem
import com.isranascimento.utils.extensions.load

class HotelListCardHolder(
    private val binding: HotelListCardItemBinding,
): BaseHotelListHolder(binding.root) {
    private var onHotelClick: ((sku: String) -> Unit)? = null

    override fun bind(item: HotelListUI) {
        val itemCasted = item as HotelListUIItem
        binding.hotelImage.load(itemCasted.image)
        binding.hotelTitle.text = itemCasted.name

        binding.amenities.removeAllViews()
        item.amenities.forEach {
            binding.amenities.addView(createAmenityTextview(it))
        }
        binding.location.text = binding.root.context.getString(R.string.hotel_list_location_text, item.city, item.state)
        binding.root.setOnClickListener {
            onHotelClick?.invoke(item.id)
        }
    }

    private fun createAmenityTextview(amenity: String) = TextView(binding.root.context).apply {
        this.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        this.text = context.getString(R.string.bullet_icon_text, amenity)
    }

    fun setClickListener(callback: (String) -> Unit) {
        onHotelClick = callback
    }
}