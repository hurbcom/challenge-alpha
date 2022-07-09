package com.edufelip.challengealpha.presentation.fragments.general_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edufelip.challengealpha.databinding.GeneralListItemBinding
import com.edufelip.challengealpha.domain.models.GeneralListMenuItem
import javax.inject.Inject

class GeneralListMenuItemAdapter @Inject constructor() :
    RecyclerView.Adapter<GeneralListMenuItemAdapter.GeneralListViewHolder>() {
    var menuList = mutableListOf<GeneralListMenuItem>()

    class GeneralListViewHolder(private val binding: GeneralListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(menuItem: GeneralListMenuItem) {
            binding.menuItem = menuItem
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): GeneralListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GeneralListItemBinding.inflate(layoutInflater, parent, false)
                return GeneralListViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneralListViewHolder {
        return GeneralListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GeneralListViewHolder, position: Int) {
        val item = menuList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    fun setMenuItems(menuItems: List<GeneralListMenuItem>) {
        menuList.clear()
        menuList.addAll(menuItems)
        notifyDataSetChanged()
    }
}