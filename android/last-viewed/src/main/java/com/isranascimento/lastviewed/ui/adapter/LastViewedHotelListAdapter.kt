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
        return LastViewedHotelHolder(
            HotelCardItemView(parent.context)
        )
    }

    override fun onBindViewHolder(holder: LastViewedHotelHolder, position: Int) {
        holder.bind(currentList[position])
        holder.bindListener(currentList[position], contract)
    }

    interface LastViewedHotelListAdapterContract: HotelCardItemView.HotelCardItemListener
}