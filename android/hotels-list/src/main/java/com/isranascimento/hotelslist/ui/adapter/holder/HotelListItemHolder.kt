package com.isranascimento.hotelslist.ui.adapter.holder

import com.isranascimento.hotelslist.databinding.HotelListCardItemBinding
import com.isranascimento.hotelslist.databinding.HotelListTitleItemBinding
import com.isranascimento.hotelslist.ui.models.HotelListUI
import com.isranascimento.hotelslist.ui.models.HotelListUIItem
import com.isranascimento.hotelslist.ui.models.HotelListUITitle

class HotelListItemHolder(
    private val binding: HotelListCardItemBinding
): BaseHotelListHolder(binding.root) {
    override fun bind(item: HotelListUI) {
        val itemCasted = item as HotelListUIItem
        binding.hotelTitle.text = itemCasted.name
    }
}