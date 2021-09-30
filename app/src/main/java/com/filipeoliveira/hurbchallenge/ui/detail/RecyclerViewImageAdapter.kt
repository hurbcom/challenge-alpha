package com.filipeoliveira.hurbchallenge.ui.detail

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.filipeoliveira.hurbchallenge.R
import com.filipeoliveira.hurbchallenge.databinding.ItemImageViewBinding

class RecyclerViewImageAdapter(
    val onClick: (String) -> Unit
) : RecyclerView.Adapter<RecyclerViewImageAdapter.ViewHolder>() {

    private val urlList = mutableListOf<String>()

    inner class ViewHolder(val binding: ItemImageViewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(url: String){
            binding.root.layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )

            binding.root.setOnClickListener {
                onClick(url)
            }

            if (url.isNotEmpty()){
                Glide.with(binding.root.context)
                    .load(url)
                    .placeholder(R.drawable.ic_image_placeholder)
                    .addListener(object : RequestListener<Drawable>{
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            val index = (urlList as MutableList<String>).indexOf(url)
                            val wasRemoved = (urlList as MutableList<String>).remove(url)
                            if (wasRemoved){
                                notifyItemRemoved(index)
                            }
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }

                    })
                    .into(binding.itemImageView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ItemImageViewBinding.inflate(inflater)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val url = urlList[position]
        holder.bind(url)
    }

    override fun getItemCount(): Int = urlList.size

    fun setData(imageUrlList: List<String>) {
        urlList.addAll(imageUrlList)
        notifyDataSetChanged()
    }
}
