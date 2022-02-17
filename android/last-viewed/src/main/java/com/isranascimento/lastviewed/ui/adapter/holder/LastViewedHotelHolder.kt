package com.isranascimento.lastviewed.ui.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import com.isranascimento.theme.hotel.HotelCardItem
import com.isranascimento.theme.hotel.HotelCardItemView

class LastViewedHotelHolder(
    private val cardItemView: HotelCardItemView
): RecyclerView.ViewHolder(cardItemView) {
    fun bind(card: HotelCardItem) {
        cardItemView.bind(card)
    }

    fun bindListener(
        hotel: HotelCardItem,
        listener: HotelCardItemView.HotelCardItemListener
    ) {
        cardItemView.bindListener(hotel, listener)
    }
}