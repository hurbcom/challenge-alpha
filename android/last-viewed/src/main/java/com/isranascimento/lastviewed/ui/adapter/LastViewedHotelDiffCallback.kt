package com.isranascimento.lastviewed.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.isranascimento.theme.hotel.HotelCardItem

class LastViewedHotelDiffCallback: DiffUtil.ItemCallback<HotelCardItem>() {

    override fun areItemsTheSame(oldItem: HotelCardItem, newItem: HotelCardItem): Boolean {
        return oldItem.areItemsTheSame(newItem)
    }

    override fun areContentsTheSame(oldItem: HotelCardItem, newItem: HotelCardItem): Boolean {
        return oldItem.areContentTheSame(newItem)
    }
}