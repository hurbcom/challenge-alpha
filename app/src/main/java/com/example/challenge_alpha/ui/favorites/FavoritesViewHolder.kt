package com.example.challenge_alpha.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.challenge_alpha.R
import com.example.challenge_alpha.data.ResultDetailRelation
import com.example.challenge_alpha.ui.resultDetail.AmenitiesAdapter
import com.example.challenge_alpha.ui.results.ResultsViewHolder
import java.text.NumberFormat
import java.util.*

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