package com.edufelip.challengealpha.presentation.fragments.category_list.category_list_people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edufelip.challengealpha.databinding.CategoryListItemBinding
import com.edufelip.challengealpha.domain.models.People
import javax.inject.Inject

class CategoryListPeopleItemAdapter @Inject constructor() :
    RecyclerView.Adapter<CategoryListPeopleItemAdapter.CategoryListViewHolder>() {
    var adapterList = mutableListOf<People>()

    class CategoryListViewHolder(private val binding: CategoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: People) {
            binding.categoryListText.text = character.name
            Glide.with(binding.root.context).load(character.imageUrl)
                .into(binding.categoryListItemImageView)
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CategoryListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CategoryListItemBinding.inflate(layoutInflater, parent, false)
                return CategoryListViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListViewHolder {
        return CategoryListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CategoryListViewHolder, position: Int) {
        val item = adapterList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return adapterList.size
    }

    fun setItems(items: List<People>) {
        adapterList.clear()
        adapterList.addAll(items)
        notifyDataSetChanged()
    }
}