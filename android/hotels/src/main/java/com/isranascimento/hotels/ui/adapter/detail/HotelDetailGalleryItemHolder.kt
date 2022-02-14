package com.isranascimento.hotels.ui.adapter.detail

import androidx.recyclerview.widget.RecyclerView
import com.isranascimento.hotels.databinding.HotelDetailGalleryHolderBinding
import com.isranascimento.utils.extensions.load

class HotelDetailGalleryItemHolder(
    private val binding: HotelDetailGalleryHolderBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(image: String) {
        binding.image.load(image)
    }
}