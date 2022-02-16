package com.isranascimento.hotels.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.isranascimento.hotels.databinding.HotelListTitleItemBinding
import com.isranascimento.hotels.ui.adapter.HotelsListViewType.CARD
import com.isranascimento.hotels.ui.adapter.HotelsListViewType.TITLE
import com.isranascimento.hotels.ui.adapter.holder.BaseHotelListHolder
import com.isranascimento.hotels.ui.adapter.holder.HotelListCardHolder
import com.isranascimento.hotels.ui.adapter.holder.HotelListTitleHolder
import com.isranascimento.hotels.ui.models.HotelListUI
import com.isranascimento.hotels.ui.models.HotelListUIItem
import com.isranascimento.hotels.ui.models.HotelListUITitle
import com.isranascimento.theme.hotel.HotelCardItemView

class HotelsListAdapter(
    private val contract: HotelsListAdapterContract
) : ListAdapter<HotelListUI, BaseHotelListHolder>(HotelDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHotelListHolder {
        if(viewType == TITLE) {
            return HotelListTitleHolder(
                HotelListTitleItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
        return HotelListCardHolder(
            HotelCardItemView(parent.context)
        )
    }

    override fun onBindViewHolder(holder: BaseHotelListHolder, position: Int) {
        holder.bind(currentList[position])
        if(holder is HotelListCardHolder) {
            val hotel = currentList[position] as HotelListUIItem
            holder.bindListener(hotel, contract)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(currentList[position] is HotelListUITitle) {
            return TITLE
        }
        return CARD
    }

    interface HotelsListAdapterContract: HotelCardItemView.HotelCardItemListener
}