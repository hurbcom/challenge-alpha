package com.isranascimento.hoteldetail.adapter

import androidx.recyclerview.widget.RecyclerView
import com.isranascimento.hoteldetail.databinding.HotelDetailGalleryHolderBinding
import com.isranascimento.utils.extensions.load

class HotelDetailGalleryItemHolder(
    private val binding: HotelDetailGalleryHolderBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(image: String) {
        binding.image.load(image)
    }
}