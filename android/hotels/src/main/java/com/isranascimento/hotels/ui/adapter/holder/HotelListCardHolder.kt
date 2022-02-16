package com.isranascimento.hotels.ui.adapter.holder

import com.isranascimento.hotels.ui.models.HotelListUI
import com.isranascimento.hotels.ui.models.HotelListUIItem
import com.isranascimento.theme.hotel.HotelCardItem
import com.isranascimento.theme.hotel.HotelCardItemView

class HotelListCardHolder(
    private val cardItemView: HotelCardItemView
): BaseHotelListHolder(cardItemView) {
    override fun bind(item: HotelListUI) {
        cardItemView.bind((item as HotelListUIItem).card)
    }

    fun bindListener(item: HotelListUIItem, callback: HotelCardItemView.HotelCardItemListener) {
        cardItemView.bindCallback(item.card, callback)
    }
}