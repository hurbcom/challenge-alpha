package com.example.challenge_alpha.ui.resultDetail

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_alpha.model.ResultDetail
import com.example.challenge_alpha.model.ResultDetailAmenities

class AmenitiesAdapter : ListAdapter<ResultDetailAmenities, RecyclerView.ViewHolder>(REPO_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AmenitiesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        val repoItem = getItem(position)
        if (repoItem != null) {
            (holder as AmenitiesViewHolder).bind(repoItem)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<ResultDetailAmenities>() {
            override fun areItemsTheSame(oldItem: ResultDetailAmenities, newItem: ResultDetailAmenities): Boolean =
                oldItem.sku == newItem.sku

            override fun areContentsTheSame(oldItem: ResultDetailAmenities, newItem: ResultDetailAmenities): Boolean =
                oldItem == newItem
        }
    }


}