package com.isranascimento.hotels.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.isranascimento.hotels.ui.models.HotelListUI
import com.isranascimento.hotels.ui.models.HotelListUIItem
import com.isranascimento.hotels.ui.models.HotelListUITitle

class HotelDiffCallback:DiffUtil.ItemCallback<HotelListUI>() {
    override fun areItemsTheSame(oldItem: HotelListUI, newItem: HotelListUI): Boolean {
        if(oldItem is HotelListUITitle && newItem is HotelListUITitle) {
            return oldItem.starCount == newItem.starCount
        }
        if(oldItem is HotelListUIItem && newItem is HotelListUIItem) {
            return oldItem.card.id == newItem.card.id
        }
        return false
    }

    override fun areContentsTheSame(oldItem: HotelListUI, newItem: HotelListUI): Boolean {
        if(oldItem is HotelListUITitle && newItem is HotelListUITitle) {
            return oldItem.starCount == newItem.starCount
        }
        if(oldItem is HotelListUIItem && newItem is HotelListUIItem) {
            return oldItem.card.name == newItem.card.name &&
                    oldItem.card.amenities == newItem.card.amenities &&
                    oldItem.card.city == newItem.card.city &&
                    oldItem.card.image == newItem.card.image &&
                    oldItem.card.state == newItem.card.state
        }
        return false
    }
}