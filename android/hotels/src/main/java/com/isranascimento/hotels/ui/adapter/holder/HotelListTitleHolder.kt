package com.isranascimento.hotels.ui.adapter.holder

import com.isranascimento.hotels.databinding.HotelListTitleItemBinding
import com.isranascimento.hotels.ui.models.HotelListUI
import com.isranascimento.hotels.ui.models.HotelListUITitle

class HotelListTitleHolder(
    private val binding: HotelListTitleItemBinding
): BaseHotelListHolder(binding.root) {
    override fun bind(item: HotelListUI) {
        val itemCasted = item as HotelListUITitle
        binding.rating.rating = itemCasted.starCount.toFloat()
    }
}