package com.example.challenge_alpha.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_alpha.model.Suggestions

class SearchViewViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {



    fun bind(suggestions: Suggestions?) {

        if (suggestions != null) {
            binding.setVariable(com.example.challenge_alpha.BR.suggestion, suggestions)
            binding.executePendingBindings()
        }

    }


    companion object {
        fun create(parent: ViewGroup, viewType: Int): SearchViewViewHolder {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                viewType,
                parent,
                false
            )
            return SearchViewViewHolder(binding)
        }
    }
}
