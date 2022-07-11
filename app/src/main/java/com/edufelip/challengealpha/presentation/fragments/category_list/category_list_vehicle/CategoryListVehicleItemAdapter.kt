package com.edufelip.challengealpha.presentation.fragments.category_list.category_list_vehicle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.edufelip.challengealpha.databinding.CategoryListItemBinding
import com.edufelip.challengealpha.domain.models.Vehicle
import javax.inject.Inject

class CategoryListVehicleItemAdapter @Inject constructor() :
    RecyclerView.Adapter<CategoryListVehicleItemAdapter.CategoryListViewHolder>() {
    var adapterList = mutableListOf<Vehicle>()

    class CategoryListViewHolder(private val binding: CategoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(vehicle: Vehicle) {
            binding.categoryListText.text = vehicle.name
            Glide.with(binding.root.context).load(vehicle.imageUrl)
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

    fun setItems(items: List<Vehicle>) {
        adapterList.clear()
        adapterList.addAll(items)
        notifyDataSetChanged()
    }
}