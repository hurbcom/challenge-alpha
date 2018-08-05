package com.github.felipehjcosta.huchallenge.feature.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.felipehjcosta.huchallenge.feature.search.viewmodel.HotelsListViewModel

class HotelsAdapter(
        private val hotelsListViewModel: HotelsListViewModel
) : RecyclerView.Adapter<HotelsAdapter.HotelsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelsViewHolder {
        return HotelsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.hotels_item_layout, parent, false))
    }

    override fun getItemCount(): Int = hotelsListViewModel.size

    override fun onBindViewHolder(holder: HotelsViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.hotels_item_title).text = hotelsListViewModel[position]?.name
    }

    inner class HotelsViewHolder(view: View) : RecyclerView.ViewHolder(view)
}