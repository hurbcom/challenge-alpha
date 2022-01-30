package com.br.natanbrito.challenge.alpha.hotel.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import com.br.natanbrito.challenge.alpha.databinding.ImageViewPagerItemBinding
import com.br.natanbrito.challenge.data.model.results.Gallery

class ImagesViewPagerAdapter(
    private val images: List<Gallery>,
    private val onItemClicked: (Gallery) -> Unit
) : RecyclerView.Adapter<ImagesViewPagerAdapter.ImagesViewPagerViewHolder>() {

    private lateinit var binding: ImageViewPagerItemBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewPagerViewHolder {
        context = parent.context
        binding = ImageViewPagerItemBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = ImagesViewPagerViewHolder(binding)
        holder.itemView.setOnClickListener {
            onItemClicked(images[holder.adapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: ImagesViewPagerViewHolder, position: Int) {
        holder.bind(images[position], context)
    }

    class ImagesViewPagerViewHolder(private val view: ImageViewPagerItemBinding) :
        RecyclerView.ViewHolder(
            view.root
        ) {
        fun bind(gallery: Gallery, context: Context) {
            with(view) {
                val imageRequest = ImageRequest.Builder(context)
                    .data(gallery.convertFromHttpToHttps())
                    .target { drawable ->
                        val bitmap = drawable.toBitmap()
                        hotelImage.load(bitmap)
                    }
                    .build()

                ImageLoader(context).enqueue(imageRequest)
                hotelImage.contentDescription = gallery.description
            }
        }
    }

    override fun getItemCount(): Int = images.size
}
