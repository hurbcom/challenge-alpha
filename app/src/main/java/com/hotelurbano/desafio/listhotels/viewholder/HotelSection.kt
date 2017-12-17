package com.hotelurbano.desafio.listhotels.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.hotelurbano.desafio.R
import com.hotelurbano.desafio.listhotels.model.HotelItem
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection
import org.greenrobot.eventbus.EventBus

/**
 * Created by Cristian on 17/12/2017.
 */
class HotelSection constructor(private var title: String, private var list: List<HotelItem>) : StatelessSection(SectionParameters.Builder(R.layout.row_hotel)
    .headerResourceId(R.layout.section_hotel_header)
    .build()) {

    override fun getContentItemsTotal(): Int {
        return list.size
    }

    override fun getItemViewHolder(view: View): RecyclerView.ViewHolder {
        return ItemViewHolder(view)
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemHolder = holder as ItemViewHolder

        val item = list[position]

        itemHolder.hotelGallery.setImageURI(item.gallery.get(0).url)
        itemHolder.hotelName.text = item.name
        itemHolder.hotelDescription.text = item.description
        itemHolder.hotelRoot.setOnClickListener {
            EventBus.getDefault().post(item)
        }
    }

    override fun getHeaderViewHolder(view: View): RecyclerView.ViewHolder {
        return HeaderViewHolder(view)
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder?) {
        val headerHolder = holder as HeaderViewHolder?

        headerHolder!!.headerSection.text = title
    }
}