package com.example.challenge_alpha.ui.favorites


import android.view.View
import android.widget.*
import androidx.databinding.BindingAdapter
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.challenge_alpha.R
import com.example.challenge_alpha.api.HurbResponse
import com.example.challenge_alpha.model.ResultDetail
import com.example.challenge_alpha.model.ResultDetailAmenities
import com.example.challenge_alpha.model.ResultDetailGallery
import com.example.challenge_alpha.ui.resultDetail.AmenitiesAdapter
import com.example.challenge_alpha.api.Result
import com.example.challenge_alpha.data.ResultDetailRelation
import com.example.challenge_alpha.ui.results.ResultsFragmentDirections


@BindingAdapter("favorites:imageLoading", "favorites:backupImageLoading")
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

@BindingAdapter("favorites:recyclerFavorites")
fun setRecyclerViewFavorites(recyclerView: RecyclerView, data: List<ResultDetailRelation>?) {

    if (recyclerView.layoutManager == null) {

        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        val adapter = FavoritesAdapter()
        recyclerView.adapter = adapter
    }


    (recyclerView.adapter as FavoritesAdapter).submitList(data)

}


@BindingAdapter("favorites:recyclerAmenities")
fun setRecyclerViewAmenities(recyclerView: RecyclerView, data: List<ResultDetailAmenities>?) {

    if (recyclerView.layoutManager == null) {
        val layoutHorizontal =
            StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
        recyclerView.layoutManager = layoutHorizontal

        recyclerView.adapter = AmenitiesAdapter()
        recyclerView.hasFixedSize()
    }

    (recyclerView.adapter as AmenitiesAdapter).submitList(data?.take(4))

}

fun navigate(view: View, sku: String) {

    Navigation.findNavController(view)
        .navigate(FavoritesFragmentDirections.favoritesToResultDetail(sku))
}