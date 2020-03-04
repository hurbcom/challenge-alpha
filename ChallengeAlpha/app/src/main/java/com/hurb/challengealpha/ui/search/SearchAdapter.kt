package com.hurb.challengealpha.ui.search

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hurb.challengealpha.glide.GlideApp
import com.hurb.challengealpha.R
import com.hurb.challengealpha.model.Result
import com.hurb.challengealpha.util.getCurrencySymbol


class SearchAdapter(
    private val context: Context,
    private val onLastItemHandler: OnLastItemHandler,
    var results: MutableList<Result> = mutableListOf()
) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hotelTextView: TextView = itemView.findViewById(R.id.hotel_tv)
        val ratingBar: RatingBar = itemView.findViewById(R.id.rating_bar)
        val cityTextView: TextView = itemView.findViewById(R.id.city_tv)
        val hotelImageView: ImageView = itemView.findViewById(R.id.image_iv)
        val oldPriceTextView: TextView = itemView.findViewById(R.id.old_price_tv)
        val currentPriceTextView: TextView = itemView.findViewById(R.id.current_price_tv)
        val taxTextView: TextView = itemView.findViewById(R.id.tax_tv)
        val freeCancellationTextView: TextView = itemView
            .findViewById(R.id.free_cancel_tv)
        val discountTextView: TextView = itemView.findViewById(R.id.discount_tv)
        val priceUnitTextView: TextView = itemView.findViewById(R.id.price_unit_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hotel_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position >= (itemCount - 1) && itemCount != 0) {
            onLastItemHandler.onLastItem()
        }
        val result: Result = results[position]
        holder.hotelTextView.text = result.name
        holder.cityTextView.text = result.address.city
        if (result.isHotel) {
            holder.ratingBar.rating = result.stars.toFloat()
        } else {
            holder.ratingBar.visibility = View.GONE
        }
        val image: String = if (result.isHotel) result.image else result.gallery[0].url
        GlideApp.with(context).load(image).into(holder.hotelImageView)
        val currencyOriginal = if (result.isHotel) result.price.currency_original
        else "BRL"
        val currency = if (result.isHotel) result.price.currency else "BRL"
        if (result.price.old_price != 0.0 && result.price.old_price !=
            result.price.current_price
        ) {
            val oldPrice =
                "${getCurrencySymbol(currencyOriginal)} ${result.price.old_price}"
            holder.oldPriceTextView.text = oldPrice
            holder.oldPriceTextView.paintFlags = holder.oldPriceTextView.paintFlags or
                Paint.STRIKE_THRU_TEXT_FLAG
        }
        val currentPrice =
            "${getCurrencySymbol(currency)} ${result.price.current_price}"
        holder.currentPriceTextView.text = currentPrice
        if (!result.isHotel || result.price.taxes.isEmpty()) {
            holder.taxTextView.text = String.format(
                context.getString(R.string.tax_text_view),
                context.getString(R.string.without_tax_text_view),
                12
            )
        } else {
            holder.taxTextView.text = String.format(
                context.getString(R.string.tax_text_view),
                context.getString(R.string.plus_tax_text_view),
                12
            )
        }
        val discount = 100 - ((result.price.current_price * 100) /
            result.price.old_price)
        if (result.price.old_price != result.price.current_price &&
            result.price.old_price != 0.0
        ) {
            val discountText = "-${discount.toInt()}%"
            holder.discountTextView.text = discountText
            holder.discountTextView.visibility = View.VISIBLE
        } else {
            holder.discountTextView.visibility = View.GONE
        }
        holder.freeCancellationTextView.visibility =
            if (result.isHotel && result.hu_free_cancellation) View.VISIBLE
            else View.GONE
        holder.priceUnitTextView.text =
            if (result.isHotel) context.getString(R.string.price_unit_hotel_text_view)
            else context.getString(R.string.price_unit_package_or_ticket_text_view)
    }
}