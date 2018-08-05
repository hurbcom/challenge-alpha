package com.github.felipehjcosta.huchallenge.feature.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.felipehjcosta.huchallenge.feature.search.viewmodel.ListItemViewModel
import com.github.felipehjcosta.huchallenge.feature.search.viewmodel.ListViewModel

class HotelsAdapter(
        private val listViewModel: ListViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        internal const val SECTION_VIEW_TYPE = 10
        internal const val HOTEL_VIEW_TYPE = 11
    }

    override fun getItemCount(): Int = listViewModel.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            SECTION_VIEW_TYPE -> SectionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.section_item_layout, parent, false))
            else -> HotelsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.hotels_item_layout, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            SECTION_VIEW_TYPE -> bindSection(holder as? SectionViewHolder?, listViewModel[position])
            else -> bindHotel(holder as? HotelsViewHolder?, listViewModel[position])
        }
    }

    private fun bindSection(holder: SectionViewHolder?, listItemViewModel: ListItemViewModel?) {
        holder?.title?.text = listItemViewModel?.name
    }

    private fun bindHotel(holder: HotelsViewHolder?, listItemViewModel: ListItemViewModel?) {
        holder?.title?.text = listItemViewModel?.name
    }

    override fun getItemViewType(position: Int): Int {
        return if (listViewModel.isSection(position)) {
            SECTION_VIEW_TYPE
        } else {
            HOTEL_VIEW_TYPE
        }
    }

    inner class HotelsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal val title = view.findViewById<TextView>(R.id.hotels_item_title)
    }

    inner class SectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal val title = view.findViewById<TextView>(R.id.section_item_title)
    }
}