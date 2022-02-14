package com.isranascimento.hotels.ui.adapter.detail

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.isranascimento.hotels.databinding.HotelDetailGalleryHolderBinding

class HotelDetailGalleryAdapter(
    private val gallery: List<String>
): RecyclerView.Adapter<HotelDetailGalleryItemHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HotelDetailGalleryItemHolder {
        return HotelDetailGalleryItemHolder(
            HotelDetailGalleryHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HotelDetailGalleryItemHolder, position: Int) {
        holder.bind(gallery[position])
    }

    override fun getItemCount(): Int {
        return gallery.count()
    }
}