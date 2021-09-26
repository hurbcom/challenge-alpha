package com.filipeoliveira.hurbchallenge.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.filipeoliveira.hurbchallenge.R
import com.filipeoliveira.hurbchallenge.databinding.ItemImageViewBinding

class RecyclerViewImageAdapter : RecyclerView.Adapter<RecyclerViewImageAdapter.ViewHolder>() {

    private val urlList = mutableListOf<String>()

    inner class ViewHolder(val binding: ItemImageViewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(url: String){
            binding.root.layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )

            if (url.isNotEmpty()){
                Glide.with(binding.root.context)
                    .load(url)
                    .placeholder(R.drawable.ic_image_placeholder)
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
