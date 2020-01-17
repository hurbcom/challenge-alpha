package com.example.challenge_alpha.ui.results

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_alpha.model.ResultDetail

class ResultsViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(result: ResultDetail?) {

        if (result != null) {
            binding.setVariable(com.example.challenge_alpha.BR.result, result)
            binding.executePendingBindings()
        }

    }

    companion object {
        fun create(parent: ViewGroup, viewType: Int): ResultsViewHolder {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                viewType,
                parent,
                false
            )
            return ResultsViewHolder(binding)
        }
    }
}

