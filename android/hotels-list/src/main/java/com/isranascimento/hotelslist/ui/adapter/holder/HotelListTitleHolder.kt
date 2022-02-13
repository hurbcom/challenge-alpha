package com.isranascimento.hotelslist.ui.adapter.holder

import com.isranascimento.hotelslist.databinding.HotelListTitleItemBinding
import com.isranascimento.hotelslist.ui.models.HotelListUI
import com.isranascimento.hotelslist.ui.models.HotelListUITitle

class HotelListTitleHolder(
    private val binding: HotelListTitleItemBinding
): BaseHotelListHolder(binding.root) {
    override fun bind(item: HotelListUI) {
        val itemCasted = item as HotelListUITitle
        binding.rating.rating = itemCasted.starCount.toFloat()
    }
}