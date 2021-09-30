package com.filipeoliveira.hurbchallenge.ui.list

import android.view.View
import com.filipeoliveira.hurbchallenge.databinding.FragmentHotelListBinding

fun FragmentHotelListBinding.showLoading() {
    this.viewLoading.baseLoadingRoot.visibility = View.VISIBLE
    this.viewError.baseErrorRoot.visibility = View.GONE
    this.viewEmpty.baseEmptyRoot.visibility = View.GONE
}

fun FragmentHotelListBinding.showRecyclerView() {
    this.viewLoading.baseLoadingRoot.visibility = View.GONE
    this.viewError.baseErrorRoot.visibility = View.GONE
    this.viewEmpty.baseEmptyRoot.visibility = View.GONE
}

fun FragmentHotelListBinding.showError() {
    this.viewLoading.baseLoadingRoot.visibility = View.GONE
    this.viewError.baseErrorRoot.visibility = View.VISIBLE
    this.viewEmpty.baseEmptyRoot.visibility = View.GONE
}

fun FragmentHotelListBinding.showEmptyState() {
    this.viewLoading.baseLoadingRoot.visibility = View.GONE
    this.viewError.baseErrorRoot.visibility = View.GONE
    this.viewEmpty.baseEmptyRoot.visibility = View.VISIBLE
}