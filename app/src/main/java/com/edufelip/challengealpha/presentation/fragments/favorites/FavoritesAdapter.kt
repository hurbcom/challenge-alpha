package com.edufelip.challengealpha.presentation.fragments.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.edufelip.challengealpha.R
import com.edufelip.challengealpha.data.local.room.models.Favorite
import com.edufelip.challengealpha.databinding.CategoryListItemBinding
import javax.inject.Inject

class FavoritesAdapter @Inject constructor() :
    RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {
    var adapterList = mutableListOf<Favorite>()

    class FavoritesViewHolder(private val binding: CategoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Favorite) {
            Glide.with(binding.root.context).setDefaultRequestOptions(
                RequestOptions().placeholder(R.drawable.placeholder)
            ).load(item.imageUrl)
                .into(binding.categoryListItemImageView)
            binding.categoryListText.text = item.name
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): FavoritesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CategoryListItemBinding.inflate(layoutInflater, parent, false)
                return FavoritesViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val item = adapterList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return adapterList.size
    }

    fun setItems(menuItems: List<Favorite>) {
        adapterList.clear()
        adapterList.addAll(menuItems)
        notifyDataSetChanged()
    }
}