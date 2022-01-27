package com.br.natanbrito.challenge.alpha.list

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.br.natanbrito.challenge.alpha.R
import com.br.natanbrito.challenge.alpha.databinding.HotelItemBinding
import com.br.natanbrito.challenge.alpha.utils.prepareCurrencyText
import com.br.natanbrito.challenge.alpha.utils.prepareLocationText
import com.br.natanbrito.challenge.data.model.results.AmenityResults
import com.br.natanbrito.challenge.data.model.results.Result

const val INITIAL_POSITION = 0
const val MAX_AMENITIES_COUNT = 3

class HotelAdapter(private val hotels: List<Result>) :
    ListAdapter<Result, HotelAdapter.HotelViewHolder>(HotelAdapter) {

    private lateinit var binding: HotelItemBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        context = parent.context
        binding = HotelItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return HotelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        holder.bind(hotels[position], context)
    }

    class HotelViewHolder(private val view: HotelItemBinding) : RecyclerView.ViewHolder(view.root) {

        fun bind(result: Result, context: Context) {
            with(view) {
                hotelImage.load(result.convertFromHttpToHttps())
                hotelName.text = result.name
                hotelPrice.prepareCurrencyText(result.price)
                hotelLocation.prepareLocationText(result.address)
                val amenities = setupAmenities(result.amenities, context)

                hotelAmenities.text = if (result.amenities.size > 2) context.getString(
                    R.string.more_than_three_amenities,
                    amenities
                ) else amenities
            }
        }

        private fun setupAmenities(amenities: List<AmenityResults>, context: Context) =
            TextUtils.join(
                ", ",
                amenities.filter {
                    !it.name.contains(context.getString(R.string.additional_cost))
                }.subList(INITIAL_POSITION, MAX_AMENITIES_COUNT).map { amenity -> amenity.name }
            )
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
