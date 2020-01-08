package com.example.challenge_alpha.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.challenge_alpha.R
import com.example.challenge_alpha.db.ResultDetailRelation
import com.example.challenge_alpha.model.ResultDetail

class FavoritesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val imageHotel: ImageView = itemView.findViewById(R.id.image_hotel)
    private val nameHotel: TextView = itemView.findViewById(R.id.name_hotel)
    private val cityHotel: TextView = itemView.findViewById(R.id.city_hotel)
    private val starsHotel: RatingBar = itemView.findViewById(R.id.stars_hotel)
    private val stateHotel: TextView = itemView.findViewById(R.id.state_hotel)
    private val priceHotel: TextView = itemView.findViewById(R.id.price_hotel)


    private var resultDetails: ResultDetailRelation? = null

    init {
        view.setOnClickListener {

            val navController = findNavController(it)

            val action = FavoritesFragmentDirections.favoritesToResultDetail(resultDetails?.resultDetail?.sku!!)
            navController.navigate(action)

        }

    }


    fun bind(result: ResultDetailRelation?) {

        if (result == null) {

        } else {
            loadData(result)
        }

    }

    private fun loadData(result: ResultDetailRelation) {
        this.resultDetails = result

        nameHotel.text = result.resultDetail?.name
        cityHotel.text = result.resultDetail?.address?.city
        stateHotel.text = result.resultDetail?.address?.state
        priceHotel.text = result.resultDetail?.price?.amount.toString()
        starsHotel.rating = if (result.resultDetail?.stars == null) {
            starsHotel.visibility = View.INVISIBLE
            0f
        } else result.resultDetail?.stars!!

        imageHotel.load(result.resultDetail?.image)

    }

    companion object {
        fun create(parent: ViewGroup): FavoritesViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.viewholder_results, parent, false)
            return FavoritesViewHolder(view)
        }
    }
}