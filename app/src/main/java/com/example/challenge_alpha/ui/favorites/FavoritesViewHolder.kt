package com.example.challenge_alpha.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_alpha.data.ResultDetailRelation

class FavoritesViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(result: ResultDetailRelation?) {

        if (result != null) {
            binding.setVariable(com.example.challenge_alpha.BR.result, result)
            binding.executePendingBindings()
        }

    }

    companion object {
        fun create(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                viewType,
                parent,
                false
            )
            return FavoritesViewHolder(binding)
        }
    }
}