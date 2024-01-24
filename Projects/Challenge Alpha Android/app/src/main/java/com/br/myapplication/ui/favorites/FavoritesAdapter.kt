package com.br.myapplication.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.myapplication.data.model.Favorite

import com.br.myapplication.databinding.ItemListBinding
import com.squareup.picasso.Picasso

class FavoritesAdapter(
    private val list: List<Favorite>
) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)

        return FavoritesViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val item = list[position]
        holder.binding.itemListName.text = item.firstProperty
        holder.binding.itemDetailList.text = item.thirdProperty
        holder.binding.itemListThird.text = item.fourthProperty
        Picasso.get()
            .load(item.secondProperty)
            .into(holder.binding.itemListImage)
    }

    class FavoritesViewHolder(itemListBinding: ItemListBinding) :
        RecyclerView.ViewHolder(itemListBinding.root) {
        val binding = itemListBinding
    }
}