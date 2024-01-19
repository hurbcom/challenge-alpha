package com.br.myapplication.ui.film

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.br.myapplication.data.model.Film
import com.br.myapplication.databinding.ItemListBinding
import com.squareup.picasso.Picasso

class FilmsAdapter(
    private val list: List<Film>
) : RecyclerView.Adapter<FilmsAdapter.FilmsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(inflater, parent, false)

        return FilmsViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        val item = list[position]
        holder.binding.itemListName.text = item.title
        Picasso.get()
            .load(item.image)
            .into(holder.binding.itemListImage)
    }

    class FilmsViewHolder(itemListBinding: ItemListBinding) :
        RecyclerView.ViewHolder(itemListBinding.root){
        val binding = itemListBinding
    }
}
