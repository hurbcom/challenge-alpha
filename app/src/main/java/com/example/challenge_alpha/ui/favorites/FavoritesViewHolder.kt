package com.example.challenge_alpha.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.challenge_alpha.R
import com.example.challenge_alpha.data.ResultDetailRelation
import com.example.challenge_alpha.ui.resultDetail.AmenitiesAdapter
import java.text.NumberFormat
import java.util.*

class FavoritesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val imageResults: ImageView = itemView.findViewById(R.id.image_results)
    private val nameResults: TextView = itemView.findViewById(R.id.name_results)
    private val locationResults: TextView = itemView.findViewById(R.id.location_results)
    private val starsResults: RatingBar = itemView.findViewById(R.id.stars_results)
    private val priceResults: TextView = itemView.findViewById(R.id.price_results)
    private val divider: View = itemView.findViewById(R.id.divider)
    val amenities: RecyclerView = itemView.findViewById(R.id.recyclerAmenities_results)


    private var resultDetails: ResultDetailRelation? = null

    init {
        view.setOnClickListener {

            val navController = findNavController(it)

            val action = FavoritesFragmentDirections.favoritesToResultDetail(resultDetails?.resultDetail?.sku!!)
            navController.navigate(action)

        }

    }


    fun bind(result: ResultDetailRelation?) {

        if (result != null) {
            loadData(result)
        }

    }

    private fun loadData(result: ResultDetailRelation) {
        this.resultDetails = result

        nameResults.text = result.resultDetail?.name
        locationResults.text = itemView.context.resources.getString(
            R.string.location_display,
            result.resultDetail?.address?.city,
            result.resultDetail?.address?.state
        )

        val priceFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        priceFormat.currency = Currency.getInstance(result.resultDetail?.price?.currency?: "BRL")
        val priceResult = priceFormat.format(result.resultDetail?.price?.amount)

        priceResults.text = priceResult


        starsResults.rating = if (result.resultDetail?.stars == null) {
            starsResults.visibility = View.GONE
            5f
        } else {
            starsResults.visibility = View.VISIBLE
            result.resultDetail?.stars!!
        }


        if (!result.resultDetailAmenities.isNullOrEmpty()) {

            amenities.visibility = View.VISIBLE
            divider.visibility = View.VISIBLE
            val layout =
                StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
            amenities.layoutManager = layout

            val amenitiesAdapter = AmenitiesAdapter()

            amenities.adapter = amenitiesAdapter
            amenities.hasFixedSize()

            amenitiesAdapter.submitList(result.resultDetailAmenities.take(3))

        }

        val imageLoad: String? =
            result.resultDetail?.image ?: result.resultDetailGallery.firstOrNull()?.url
        Glide.with(itemView)
            .load(imageLoad)
            .fitCenter()
            .centerCrop()
            .placeholder(R.drawable.ic_refresh)
            .error(R.drawable.ic_sync_problem)
            .fallback(R.drawable.ic_sync_problem)
            .into(imageResults)

    }

    companion object {
        fun create(parent: ViewGroup): FavoritesViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.viewholder_results, parent, false)
            return FavoritesViewHolder(view)
        }
    }
}