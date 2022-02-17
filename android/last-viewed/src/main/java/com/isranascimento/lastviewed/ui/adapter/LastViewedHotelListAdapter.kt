package com.isranascimento.lastviewed.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.isranascimento.lastviewed.ui.adapter.holder.LastViewedHotelHolder
import com.isranascimento.theme.hotel.HotelCardItem
import com.isranascimento.theme.hotel.HotelCardItemView

class LastViewedHotelListAdapter(
    private val contract: LastViewedHotelListAdapterContract
): ListAdapter<HotelCardItem, LastViewedHotelHolder>(LastViewedHotelDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastViewedHotelHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: LastViewedHotelHolder, position: Int) {
        TODO("Not yet implemented")
    }

    interface LastViewedHotelListAdapterContract: HotelCardItemView.HotelCardItemListener
}