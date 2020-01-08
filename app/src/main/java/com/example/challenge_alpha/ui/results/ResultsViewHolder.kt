package com.example.challenge_alpha.ui.results

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
import com.example.challenge_alpha.model.ResultDetail

class ResultsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val recyclerTitle: TextView = itemView.findViewById(R.id.title_hotel)
    private val imageHotel: ImageView = itemView.findViewById(R.id.image_hotel)
    private val nameHotel: TextView = itemView.findViewById(R.id.name_hotel)
    private val starsHotel: RatingBar = itemView.findViewById(R.id.stars_hotel)
    private val cityHotel: TextView = itemView.findViewById(R.id.city_hotel)
    private val stateHotel: TextView = itemView.findViewById(R.id.state_hotel)
    private val priceHotel: TextView = itemView.findViewById(R.id.price_hotel)
    private val amenities1: TextView = itemView.findViewById(R.id.amenities1_hotel)
    private val amenities2: TextView = itemView.findViewById(R.id.amenities2_hotel)
    private val amenities3: TextView = itemView.findViewById(R.id.amenities3_hotel)


    private var resultDetails: ResultDetail? = null

    init {
        view.setOnClickListener {

            val navController = findNavController(it)

            val action = ResultsFragmentDirections.resultsToResultDetail(resultDetails!!.sku)
            navController.navigate(action)

        }

    }


    fun bind(result: ResultDetail?) {

        if (result == null) {

        } else {
            loadData(result)
        }

    }

    private fun loadData(result: ResultDetail) {
        this.resultDetails = result

        recyclerTitle.text = result.recyclerTitle.let { isTitle ->
            if (isTitle) {
                recyclerTitle.visibility = View.VISIBLE
            }
            "${result.stars?.toInt().toString()} Estrelas"
        }

        nameHotel.text = result.name
        cityHotel.text = result.address?.city
        stateHotel.text = result.address?.state
        priceHotel.text = result.price.amount.toString()
        starsHotel.rating = if (result.stars == null) {
            starsHotel.visibility = View.INVISIBLE
            0f
        } else result.stars!!


        amenities1.text = result.amenities.getOrNull(0)?.name.let {
            amenities1.visibility = View.VISIBLE
            it
        }
        amenities2.text = result.amenities.getOrNull(1)?.name.let {
            amenities2.visibility = View.VISIBLE
            it
        }
        amenities3.text = result.amenities.getOrNull(2)?.name.let {
            amenities3.visibility = View.VISIBLE
            it
        }

        imageHotel.load(result.image)

    }

    companion object {
        fun create(parent: ViewGroup): ResultsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.viewholder_results, parent, false)
            return ResultsViewHolder(view)
        }
    }
}