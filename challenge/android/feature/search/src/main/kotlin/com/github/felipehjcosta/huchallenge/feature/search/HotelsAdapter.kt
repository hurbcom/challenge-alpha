package com.github.felipehjcosta.huchallenge.feature.search

import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.felipehjcosta.huchallenge.feature.search.viewmodel.ListItemViewModel
import com.github.felipehjcosta.huchallenge.feature.search.viewmodel.ListViewModel

class HotelsAdapter(
        private val listViewModel: ListViewModel
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        internal const val HOTEL_SECTION_VIEW_TYPE = 10
        internal const val HOTEL_VIEW_TYPE = 20
        internal const val PACKAGE_SECTION_VIEW_TYPE = 30
        internal const val PACKAGE_VIEW_TYPE = 40
    }

    override fun getItemCount(): Int = listViewModel.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PACKAGE_SECTION_VIEW_TYPE -> PackageSectionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.package_section_item_layout, parent, false))
            PACKAGE_VIEW_TYPE -> HotelsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.hotels_item_layout, parent, false))
            HOTEL_SECTION_VIEW_TYPE -> HotelSectionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.section_item_layout, parent, false))
            else -> HotelsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.hotels_item_layout, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            PACKAGE_SECTION_VIEW_TYPE -> bindPackageSection(holder as? PackageSectionViewHolder?, listViewModel[position])
            PACKAGE_VIEW_TYPE -> bindHotel(holder as? HotelsViewHolder?, listViewModel[position])
            HOTEL_SECTION_VIEW_TYPE -> bindSection(holder as? HotelSectionViewHolder?, listViewModel[position])
            else -> bindHotel(holder as? HotelsViewHolder?, listViewModel[position])
        }
    }

    private fun bindPackageSection(holder: PackageSectionViewHolder?, listItemViewModel: ListItemViewModel?) {}

    private fun bindSection(hotel: HotelSectionViewHolder?, listItemViewModel: ListItemViewModel?) {
        hotel?.let {
            val name = listItemViewModel?.name ?: ""
            it.title?.text = it.resources.getString(R.string.hotel_section_title, name)
        }
    }

    private fun bindHotel(holder: HotelsViewHolder?, listItemViewModel: ListItemViewModel?) {
        holder?.let {
            it.title.text = listItemViewModel?.name
            it.address.text = it.resources.getString(R.string.hotel_address, listItemViewModel?.city, listItemViewModel?.state)
            it.price.text = listItemViewModel?.price
            val amenity1 = listItemViewModel?.amenity1
            val amenity2 = listItemViewModel?.amenity2
            val amenity3 = listItemViewModel?.amenity3

            val isAmenitiesAbsent = amenity1.isNullOrBlank() && amenity2.isNullOrBlank() && amenity3.isNullOrBlank()
            val amenityVisibility = if (isAmenitiesAbsent) View.GONE else View.VISIBLE

            it.amenityTitle.visibility = amenityVisibility
            it.amenity1.text = amenity1
            it.amenity1.visibility = amenityVisibility
            it.amenity2.text = amenity2
            it.amenity2.visibility = amenityVisibility
            it.amenity3.text = amenity3
            it.amenity3.visibility = amenityVisibility
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            listViewModel.isPackageSection(position) -> PACKAGE_SECTION_VIEW_TYPE
            listViewModel.isPackage(position) -> PACKAGE_VIEW_TYPE
            listViewModel.isSection(position) -> HOTEL_SECTION_VIEW_TYPE
            else -> HOTEL_VIEW_TYPE
        }
    }

    inner class HotelSectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal val title = view.findViewById<TextView>(R.id.section_item_title)
    }

    inner class HotelsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal val title = view.findViewById<TextView>(R.id.item_title)
        internal val address = view.findViewById<TextView>(R.id.item_address)
        internal val price = view.findViewById<TextView>(R.id.item_price)
        internal val amenityTitle = view.findViewById<TextView>(R.id.item_amenity_title)
        internal val amenity1 = view.findViewById<TextView>(R.id.item_amenity_first)
        internal val amenity2 = view.findViewById<TextView>(R.id.item_amenity_second)
        internal val amenity3 = view.findViewById<TextView>(R.id.item_amenity_third)
    }

    inner class PackageSectionViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private val RecyclerView.ViewHolder.resources: Resources
        get() = itemView.resources
}