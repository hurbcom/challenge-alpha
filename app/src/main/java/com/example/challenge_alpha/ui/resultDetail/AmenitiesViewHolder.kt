package com.example.challenge_alpha.ui.resultDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge_alpha.R
import com.example.challenge_alpha.model.ResultDetailAmenities

class AmenitiesViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    private val amenities: TextView = itemView.findViewById(R.id.name_amenities)

    private var resultDetailAmenities: ResultDetailAmenities? = null


    fun bind(result: ResultDetailAmenities?) {

        if (result == null) {

        } else {
            loadData(result)
        }

    }

    private fun loadData(result: ResultDetailAmenities) {
        this.resultDetailAmenities = result

        amenities.text = result.name


    }

    companion object {
        fun create(parent: ViewGroup): AmenitiesViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.viewholder_amenities, parent, false)
            return AmenitiesViewHolder(view)
        }
    }
}