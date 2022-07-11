package com.edufelip.challengealpha.presentation.fragments.category_list.category_list_film

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.edufelip.challengealpha.R
import com.edufelip.challengealpha.databinding.CategoryListItemBinding
import com.edufelip.challengealpha.domain.models.Film
import javax.inject.Inject

class CategoryListFilmItemAdapter @Inject constructor() :
    RecyclerView.Adapter<CategoryListFilmItemAdapter.CategoryListViewHolder>() {
    private var adapterList = mutableListOf<Film>()

    class CategoryListViewHolder(private val binding: CategoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(film: Film) {
            binding.root.setOnClickListener {
                binding.root.findNavController().navigate(
                    CategoryListFilmFragmentDirections
                        .actionCategoryListFilmFragmentToCategoryDetailFilmFragment(film)
                )
            }
            binding.categoryListText.text = film.title
            Glide.with(binding.root.context).setDefaultRequestOptions(
                RequestOptions().placeholder(R.drawable.placeholder)
            ).load(film.imageUrl)
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

    fun setItems(items: List<Film>) {
        adapterList.clear()
        adapterList.addAll(items)
        notifyDataSetChanged()
    }
}