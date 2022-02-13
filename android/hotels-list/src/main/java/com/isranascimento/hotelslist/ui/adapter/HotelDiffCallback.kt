package com.isranascimento.hotelslist.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.isranascimento.hotelslist.ui.models.HotelListUI
import com.isranascimento.hotelslist.ui.models.HotelListUIItem
import com.isranascimento.hotelslist.ui.models.HotelListUITitle

class HotelDiffCallback:DiffUtil.ItemCallback<HotelListUI>() {
    override fun areItemsTheSame(oldItem: HotelListUI, newItem: HotelListUI): Boolean {
        if(oldItem is HotelListUITitle && newItem is HotelListUITitle) {
            return oldItem.starCount == newItem.starCount
        }
        if(oldItem is HotelListUIItem && newItem is HotelListUIItem) {
            return oldItem.sku == newItem.sku
        }
        return false
    }

    override fun areContentsTheSame(oldItem: HotelListUI, newItem: HotelListUI): Boolean {
        if(oldItem is HotelListUITitle && newItem is HotelListUITitle) {
            return oldItem.starCount == newItem.starCount
        }
        if(oldItem is HotelListUIItem && newItem is HotelListUIItem) {
            return oldItem.name == newItem.name &&
                    oldItem.amenities == newItem.amenities &&
                    oldItem.city == newItem.city &&
                    oldItem.image == newItem.image &&
                    oldItem.state == newItem.state
        }
        return false
    }
}