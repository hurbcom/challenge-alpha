package com.example.challenge_alpha.ui.results

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_alpha.R
import com.example.challenge_alpha.model.ResultDetail

class ResultsAdapter : ListAdapter<ResultDetail, RecyclerView.ViewHolder>(REPO_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ResultsViewHolder.create(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        val repoItem = getItem(position)


        if (repoItem != null) {
            (holder as ResultsViewHolder).bind(repoItem)
        }
    }

    override fun getItemViewType(position: Int) = R.layout.viewholder_results

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<ResultDetail>() {
            override fun areItemsTheSame(oldItem: ResultDetail, newItem: ResultDetail): Boolean =
                oldItem.sku == newItem.sku

            override fun areContentsTheSame(oldItem: ResultDetail, newItem: ResultDetail): Boolean =
                oldItem == newItem
        }
    }


}