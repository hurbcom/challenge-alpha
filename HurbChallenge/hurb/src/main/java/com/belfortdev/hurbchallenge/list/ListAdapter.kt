package com.belfortdev.hurbchallenge.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.belfortdev.hurbchallenge.R
import com.belfortdev.hurbchallenge.core.model.SearchDomain
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.hotel_item.view.*

class ListAdapter(private val picasso: Picasso) :
        RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var data = emptyList<SearchDomain.Hotel>()

    private enum class SortType {
        RATING
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.hotel_item,
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(data[position], picasso)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setData(data: List<SearchDomain.Hotel>?) {
        this.data = data ?: this.data
        sortDataBy(SortType.RATING, false)
        notifyDataSetChanged()
    }

    private fun sortDataBy(type: SortType, ascending: Boolean) {
        val sortedList = when (type) {
            SortType.RATING -> {
                if (ascending) data.sortedWith(compareBy(SearchDomain.Hotel::stars, SearchDomain.Hotel::isHotel))
                else data.sortedWith(compareByDescending<SearchDomain.Hotel> { it.stars }.thenBy { it.isHotel })
            }
        }
        this.data = sortedList
    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(hotel: SearchDomain.Hotel, picasso: Picasso) {
            picasso.load(hotel.image)
                    .placeholder(R.drawable.placeholder)
                    .into(itemView.hotelIV)

            with(itemView) {
                titleTV.text = hotel.name
                cityTV.text = hotel.address?.city
                setPriceTextView(hotel)
                hotel.stars?.let { ratingBar.rating = it }
                setFreeCancellationTextView(hotel)
            }
        }

        private fun View.setFreeCancellationTextView(hotel: SearchDomain.Hotel): Unit? {
            return hotel.huFreeCancellation?.let { freeCancellation ->
                if (freeCancellation) {
                    freeCancellationTV.visibility = VISIBLE
                }
            }
        }

        private fun View.setPriceTextView(hotel: SearchDomain.Hotel) {
            if (hotel.price?.currentPrice == null) {
                priceTV.text = ""
                currencyTV.visibility = GONE
            } else {
                priceTV.text = hotel.price?.currentPrice?.toInt().toString()
            }
        }
    }

}