package br.com.loubake.challenge_hu.hotels

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.loubake.challenge_hu.R
import br.com.loubake.challenge_hu.data.HotelResults
import com.bumptech.glide.Glide

class HotelsAdapter(var context: Context, var hotels: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val HOTEL_TITLE_VIEW_TYPE = 0
    val HOTEL_ITEM_VIEW_TYPE = 1

    override fun getItemViewType(position: Int): Int {
        if (hotels[position] is String) {
            return HOTEL_TITLE_VIEW_TYPE
        }
        return HOTEL_ITEM_VIEW_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == HOTEL_TITLE_VIEW_TYPE) {
            val view = LayoutInflater.from(context).inflate(R.layout.vh_hotels_title, parent, false)
            return HotelGroupTitleViewHolder(view)
        }
        val view = LayoutInflater.from(context).inflate(R.layout.vh_hotels_item, parent, false)
        return HotelItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val content = hotels[position]

        if (content is String) {
            (holder as HotelGroupTitleViewHolder).title.text = content
            return
        }
        if (content is HotelResults.Hotel) {
            if (TextUtils.isEmpty(content.image)) {
                (holder as HotelItemViewHolder).image.visibility = View.GONE
            } else {
                (holder as HotelItemViewHolder).image.visibility = View.VISIBLE
                Glide.with(context).load(content.image).into(holder.image)
            }

            holder.name.text = content.name
            holder.city.text = content.address?.city
            if (content.address?.city == null) {
                holder.city.visibility = View.GONE
            }
            holder.state.text = content.address?.state
            if (content.address?.state == null) {
                holder.state.visibility = View.GONE
            }

            if (!content.amenities?.isEmpty()!!) {
                var amenities = ""
                for (i in 0 .. content.amenities?.size!!) {
                    if (i == content.amenities!!.size - 1 || i == 2) {
                        amenities += content.amenities!![i].name
                        break
                    }
                    amenities += content.amenities!![i].name + ", "
                }
                holder.amenities.text = context.getString(R.string.amenities, amenities)
            } else {
                holder.amenities.visibility = View.GONE
            }

            if (content.isHotel) {
                holder.price.text = context.getString(R.string.price, (content.price as HotelResults.Hotel.HotelPrice).currentPrice)
            } else if (content.isPackage) {
                holder.price.text = context.getString(R.string.price, (content.price as HotelResults.Hotel.PackagePrice).currentPrice)
            }
        }
    }

    override fun getItemCount(): Int {
        return hotels.size
    }

    class HotelGroupTitleViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var title = itemview.findViewById<TextView>(R.id.hotel_title)
    }

    class HotelItemViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var image = itemview.findViewById<ImageView>(R.id.hotel_image)
        var city = itemview.findViewById<TextView>(R.id.hotel_city)
        var state = itemview.findViewById<TextView>(R.id.hotel_state)
        var name = itemview.findViewById<TextView>(R.id.hotel_name)
        var price = itemview.findViewById<TextView>(R.id.hotel_price)
        var amenities = itemview.findViewById<TextView>(R.id.hotel_amenities)
    }
}