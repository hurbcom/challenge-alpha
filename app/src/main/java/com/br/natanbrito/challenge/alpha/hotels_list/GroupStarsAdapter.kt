package com.br.natanbrito.challenge.alpha.hotels_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.br.natanbrito.challenge.alpha.R
import com.br.natanbrito.challenge.alpha.databinding.GroupStarsItemBinding
import com.br.natanbrito.challenge.data.model.results.Result

class GroupStarsAdapter(private val hotels: List<Result>) :
    ListAdapter<Result, GroupStarsAdapter.GroupStarsViewHolder>(GroupStarsAdapter) {

    private lateinit var binding: GroupStarsItemBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupStarsViewHolder {
        context = parent.context
        binding = GroupStarsItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return GroupStarsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GroupStarsViewHolder, position: Int) {

        holder.bind(hotels,position,context)
    }

    class GroupStarsViewHolder(private val view: GroupStarsItemBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(result: List<Result>, position: Int, context: Context) {
            with(view) {
                starCount.text = context.getString(R.string.hotels_by_stars, result[position].stars)

                val hotels = result.filter { it.stars == result[position].stars }


                val adapter = HotelAdapter(hotels)
                hotelsList.adapter = adapter
                adapter.submitList(hotels)
            }
        }
    }

    private companion object : DiffUtil.ItemCallback<Result>() {

        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }
}

