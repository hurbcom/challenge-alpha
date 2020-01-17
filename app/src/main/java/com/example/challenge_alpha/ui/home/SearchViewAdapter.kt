package com.example.challenge_alpha.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_alpha.R
import com.example.challenge_alpha.model.Suggestions

class SearchViewAdapter : ListAdapter<Suggestions, RecyclerView.ViewHolder>(REPO_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SearchViewViewHolder.create(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            (holder as SearchViewViewHolder).bind(repoItem)
        }
    }

    override fun getItemViewType(position: Int) = R.layout.viewholder_searchview

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Suggestions>() {
            override fun areItemsTheSame(oldItem: Suggestions, newItem: Suggestions): Boolean =
                oldItem.filter == oldItem.filter

            override fun areContentsTheSame(oldItem: Suggestions, newItem: Suggestions): Boolean =
                oldItem == newItem
        }
    }


}