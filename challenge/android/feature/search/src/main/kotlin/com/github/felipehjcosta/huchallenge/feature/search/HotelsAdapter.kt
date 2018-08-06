package com.github.felipehjcosta.huchallenge.feature.search

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
            PACKAGE_VIEW_TYPE -> PackageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.package_item_layout, parent, false))
            HOTEL_SECTION_VIEW_TYPE -> HotelSectionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.section_item_layout, parent, false))
            else -> HotelsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.hotels_item_layout, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            PACKAGE_SECTION_VIEW_TYPE -> bindPackageSection(holder as? PackageSectionViewHolder?, listViewModel[position])
            PACKAGE_VIEW_TYPE -> bindPackage(holder as? PackageViewHolder?, listViewModel[position])
            HOTEL_SECTION_VIEW_TYPE -> bindSection(holder as? HotelSectionViewHolder?, listViewModel[position])
            else -> bindHotel(holder as? HotelsViewHolder?, listViewModel[position])
        }
    }

    private fun bindPackageSection(holder: PackageSectionViewHolder?, listItemViewModel: ListItemViewModel?) {}

    private fun bindPackage(holder: PackageViewHolder?, listItemViewModel: ListItemViewModel?) {
        holder?.title?.text = listItemViewModel?.name
    }

    private fun bindSection(hotel: HotelSectionViewHolder?, listItemViewModel: ListItemViewModel?) {
        hotel?.title?.text = listItemViewModel?.name
    }

    private fun bindHotel(holder: HotelsViewHolder?, listItemViewModel: ListItemViewModel?) {
        holder?.title?.text = listItemViewModel?.name
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
    }

    inner class PackageSectionViewHolder(view: View) : RecyclerView.ViewHolder(view)

    inner class PackageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal val title = view.findViewById<TextView>(R.id.item_title)
    }
}