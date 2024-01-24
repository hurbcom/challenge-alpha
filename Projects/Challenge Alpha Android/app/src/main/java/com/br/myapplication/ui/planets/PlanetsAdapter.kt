package com.br.myapplication.ui.planets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.br.myapplication.R
import com.br.myapplication.data.model.Planet
import com.br.myapplication.databinding.ItemListBinding
import com.squareup.picasso.Picasso

class PlanetsAdapter(
    private val action : (item: Planet) -> Unit,
    private val callDetail : (item: Planet) -> Unit
) : PagingDataAdapter<Planet, PlanetsAdapter.PlanetViewHolder>(DIFF_CALLBACK){

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Planet>() {
            override fun areItemsTheSame(oldItem: Planet, newItem: Planet): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Planet, newItem: Planet): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)

        return PlanetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.itemListName.text = item?.name
        holder.binding.itemDetailList.text = item?.population
        holder.binding.itemListThird.text = item?.climate

        holder.itemView.setOnClickListener {
            item?.let {

                callDetail.invoke(item)
            }
        }

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

    class PlanetViewHolder(itemListBinding: ItemListBinding) :
        RecyclerView.ViewHolder(itemListBinding.root){
        val binding = itemListBinding
    }
}
