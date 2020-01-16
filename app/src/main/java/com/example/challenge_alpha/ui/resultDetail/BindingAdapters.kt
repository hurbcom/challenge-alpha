package com.example.challenge_alpha.ui.resultDetail


import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.challenge_alpha.R
import com.example.challenge_alpha.model.ResultDetailAmenities
import com.example.challenge_alpha.model.ResultDetailGallery


@BindingAdapter("resultDetail:imageLoading", "resultDetail:backupImageLoading")
fun imageLoading(view: ImageView, imageUrl: String?, backupImageLoading: ResultDetailGallery?) {


    val imageLoad: String? =
        imageUrl ?: backupImageLoading?.url
    Glide.with(view.context)
        .load(imageLoad)
        .fitCenter()
        .centerCrop()
        .placeholder(R.drawable.ic_refresh)
        .error(R.drawable.ic_sync_problem)
        .fallback(R.drawable.ic_sync_problem)
        .into(view)


}


@BindingAdapter("resultDetail:recyclerAmenities")
fun setRecyclerViewProperties(recyclerView: RecyclerView, data: List<ResultDetailAmenities>?) {

    if (recyclerView.layoutManager == null) {
        val layoutHorizontal =
            StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
        recyclerView.layoutManager = layoutHorizontal

        recyclerView.adapter = AmenitiesAdapter()
        recyclerView.hasFixedSize()
    }

    (recyclerView.adapter as AmenitiesAdapter).submitList(data)

}