package com.br.myapplication.ui.film

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.br.myapplication.R
import com.br.myapplication.data.model.Film
import com.br.myapplication.databinding.ItemListBinding
import com.squareup.picasso.Picasso

class FilmsAdapter(
    private val action : (item: Film) -> Unit,
    private val callDetail : (item: Film) -> Unit
) : PagingDataAdapter<Film, FilmsAdapter.FilmsViewHolder>(DIFF_CALLBACK){

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Film>() {
            override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem.episodeId == newItem.episodeId
            }

            override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)

        return FilmsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            item?.let {

                callDetail.invoke(item)
            }
        }
        holder.binding.itemListName.text = item?.title
        holder.binding.itemDetailList.text = item?.releaseDate
        holder.binding.itemListThird.text = item?.producer
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

    class FilmsViewHolder(itemListBinding: ItemListBinding) :
        RecyclerView.ViewHolder(itemListBinding.root){
        val binding = itemListBinding
    }
}
