package com.br.myapplication.ui.species

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.br.myapplication.R
import com.br.myapplication.data.model.Specie
import com.br.myapplication.databinding.ItemListBinding
import com.squareup.picasso.Picasso

class SpeciesAdapter(
    private val action : (item: Specie) -> Unit
) : PagingDataAdapter<Specie, SpeciesAdapter.SpeciesViewHolder>(DIFF_CALLBACK){

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Specie>() {
            override fun areItemsTheSame(oldItem: Specie, newItem: Specie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Specie, newItem: Specie): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)

        return SpeciesViewHolder(binding)
    }


    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.itemListName.text = item?.name
        holder.binding.itemDetailList.text = item?.eyeColors
        holder.binding.itemListThird.text = item?.language

        holder.binding.itemListFavorite.setOnClickListener {
            item?.let {
                it.isFavorite = !item.isFavorite
                action.invoke(it)
            }
            notifyItemChanged(position)
        }

        if (item != null && item.isFavorite) {
            holder.binding.itemListFavorite.setImageResource(R.drawable.ic_filled_heart)
        } else {
            holder.binding.itemListFavorite.setImageResource(R.drawable.ic_heart)
        }

        Picasso.get()
            .load(item?.image)
            .into(holder.binding.itemListImage)
    }

    class SpeciesViewHolder(itemListBinding: ItemListBinding) :
        RecyclerView.ViewHolder(itemListBinding.root){
        val binding = itemListBinding
    }
}
