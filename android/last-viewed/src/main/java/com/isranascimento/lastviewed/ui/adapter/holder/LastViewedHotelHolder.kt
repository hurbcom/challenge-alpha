package com.isranascimento.lastviewed.ui.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import com.isranascimento.theme.hotel.HotelCardItem
import com.isranascimento.theme.hotel.HotelCardItemView
import com.isranascimento.utils.extensions.screenWidthInPx

class LastViewedHotelHolder(
    private val cardItemView: HotelCardItemView
): RecyclerView.ViewHolder(cardItemView) {
    fun bind(card: HotelCardItem) {
        cardItemView.bind(card,
            (itemView.context.screenWidthInPx() * CARD_WIDTH_IN_PERCENTAGE).toInt()
        )
    }

    fun bindListener(
        hotel: HotelCardItem,
        listener: HotelCardItemView.HotelCardItemListener
    ) {
        cardItemView.bindListener(hotel, listener)
    }

    companion object {
        const val CARD_WIDTH_IN_PERCENTAGE = 0.8
    }
}