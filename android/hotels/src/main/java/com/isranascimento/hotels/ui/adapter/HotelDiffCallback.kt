package com.isranascimento.hotels.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.isranascimento.hotels.ui.models.HotelListUI
import com.isranascimento.hotels.ui.models.HotelListUIItem
import com.isranascimento.hotels.ui.models.HotelListUITitle

class HotelDiffCallback: DiffUtil.ItemCallback<HotelListUI>() {
    override fun areItemsTheSame(oldItem: HotelListUI, newItem: HotelListUI): Boolean {
        if(oldItem is HotelListUITitle && newItem is HotelListUITitle) {
            return oldItem.starCount == newItem.starCount
        }
        if(oldItem is HotelListUIItem && newItem is HotelListUIItem) {
            return oldItem.card.areItemsTheSame(newItem.card)
        }
        return false
    }

    override fun areContentsTheSame(oldItem: HotelListUI, newItem: HotelListUI): Boolean {
        if(oldItem is HotelListUITitle && newItem is HotelListUITitle) {
            return oldItem.starCount == newItem.starCount
        }
        if(oldItem is HotelListUIItem && newItem is HotelListUIItem) {
            return oldItem.card.areContentTheSame(newItem.card)
        }
        return false
    }
}