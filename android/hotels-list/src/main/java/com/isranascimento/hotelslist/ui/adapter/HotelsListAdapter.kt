package com.isranascimento.hotelslist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.isranascimento.hotelslist.databinding.HotelListCardItemBinding
import com.isranascimento.hotelslist.databinding.HotelListTitleItemBinding
import com.isranascimento.hotelslist.ui.adapter.holder.BaseHotelListHolder
import com.isranascimento.hotelslist.ui.adapter.holder.HotelListItemHolder
import com.isranascimento.hotelslist.ui.adapter.holder.HotelListTitleHolder
import com.isranascimento.hotelslist.ui.models.HotelListUI
import com.isranascimento.hotelslist.ui.models.HotelListUITitle

class HotelsListAdapter: ListAdapter<HotelListUI, BaseHotelListHolder>(HotelDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHotelListHolder {
        if(viewType == TITLE) {
            return HotelListTitleHolder(
                HotelListTitleItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
        return HotelListItemHolder(
            HotelListCardItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseHotelListHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemViewType(position: Int): Int {
        if(currentList[position] is HotelListUITitle) {
            return TITLE
        }
        return CARD
    }

    companion object {
        private const val TITLE = 0
        private const val CARD = 1
    }
}