package com.isranascimento.hotels.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.isranascimento.hotels.databinding.HotelListCardItemBinding
import com.isranascimento.hotels.databinding.HotelListTitleItemBinding
import com.isranascimento.hotels.ui.adapter.HotelsListViewType.CARD
import com.isranascimento.hotels.ui.adapter.HotelsListViewType.TITLE
import com.isranascimento.hotels.ui.adapter.holder.BaseHotelListHolder
import com.isranascimento.hotels.ui.adapter.holder.HotelListCardHolder
import com.isranascimento.hotels.ui.adapter.holder.HotelListTitleHolder
import com.isranascimento.hotels.ui.models.HotelListUI
import com.isranascimento.hotels.ui.models.HotelListUITitle

class HotelsListAdapter(private val onHotelCardClickListener: (String) -> Unit)
    : ListAdapter<HotelListUI, BaseHotelListHolder>(HotelDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHotelListHolder {
        if(viewType == TITLE) {
            return HotelListTitleHolder(
                HotelListTitleItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
        return HotelListCardHolder(
            HotelListCardItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseHotelListHolder, position: Int) {
        holder.bind(currentList[position])
        if(holder is HotelListCardHolder) {
            holder.setClickListener(onHotelCardClickListener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(currentList[position] is HotelListUITitle) {
            return TITLE
        }
        return CARD
    }
}