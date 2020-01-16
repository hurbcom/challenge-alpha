package com.example.challenge_alpha.ui.results

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.challenge_alpha.R
import com.example.challenge_alpha.model.ResultDetail
import com.example.challenge_alpha.ui.resultDetail.AmenitiesAdapter
import java.text.NumberFormat
import java.util.*

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

