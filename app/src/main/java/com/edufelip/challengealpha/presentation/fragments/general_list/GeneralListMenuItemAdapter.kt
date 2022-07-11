package com.edufelip.challengealpha.presentation.fragments.general_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.edufelip.challengealpha.R
import com.edufelip.challengealpha.databinding.GeneralListItemBinding
import com.edufelip.challengealpha.domain.models.GeneralListMenuItem
import com.edufelip.challengealpha.domain.models.GeneralListMenuItemTypeEnum
import com.edufelip.challengealpha.presentation.fragments.general_list.navigation_delegate.*
import javax.inject.Inject

class GeneralListMenuItemAdapter @Inject constructor() :
    RecyclerView.Adapter<GeneralListMenuItemAdapter.GeneralListViewHolder>() {
    var menuList = mutableListOf<GeneralListMenuItem>()

    class GeneralListViewHolder(private val binding: GeneralListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(menuItem: GeneralListMenuItem) {
            binding.menuItem = menuItem
            Glide.with(binding.root.context).setDefaultRequestOptions(
                RequestOptions().placeholder(R.drawable.placeholder)
            ).load(menuItem.image)
                .into(binding.generalListItemImageView)
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                navigateDelegateFactory(menuItem.type).navigate(binding.root.findNavController())
            }
        }

        private fun navigateDelegateFactory(type: GeneralListMenuItemTypeEnum): NavigationDelegate {
            return when(type) {
                GeneralListMenuItemTypeEnum.CHARACTERS -> NavigationPeopleDelegate()
                GeneralListMenuItemTypeEnum.FILMS -> NavigationFilmDelegate()
                GeneralListMenuItemTypeEnum.SPECIES -> NavigationSpecieDelegate()
                GeneralListMenuItemTypeEnum.STARSHIPS -> NavigationStarshipDelegate()
                GeneralListMenuItemTypeEnum.VEHICLES -> NavigationVehicleDelegate()
                GeneralListMenuItemTypeEnum.PLANETS -> NavigationPlanetDelegate()
            }
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