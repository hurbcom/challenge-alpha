package com.example.challenge_alpha.ui.results


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
import com.example.challenge_alpha.utils.recyclerTitle
import com.example.challenge_alpha.utils.toastLong


@BindingAdapter("results:imageLoading", "results:backupImageLoading")
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

@BindingAdapter("results:recyclerResults", "results:progressBar")
fun setRecyclerViewResults(
    recyclerView: RecyclerView,
    result: Result<HurbResponse>?,
    progressBar: ProgressBar
) {

    if (recyclerView.layoutManager == null) {

        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        val adapter = ResultsAdapter()
        recyclerView.adapter = adapter
    }

    when (result?.status) {
        Result.Status.SUCCESS -> {
            val sorted =
                result.data?.resultDetail?.sortedByDescending { list -> list.stars }
            sorted.let { List ->
                for (stars in 1..5) {
                    List?.firstOrNull { it.stars == stars.toFloat() }?.recyclerTitle = true
                }
                List?.firstOrNull { !it.hotelIs }?.recyclerTitle = true
            }

            result.data.let { (recyclerView.adapter as ResultsAdapter).submitList(sorted) }
            progressBar.visibility = View.GONE
        }
        Result.Status.LOADING -> {
            progressBar.visibility = View.VISIBLE
        }
        Result.Status.ERROR -> {
            progressBar.visibility = View.GONE
            recyclerView.context.toastLong(recyclerView.context.getString(R.string.results_error))
        }
    }


}


@BindingAdapter("results:recyclerAmenities")
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


@BindingAdapter("results:title")
fun setTitle(view: TextView, result: ResultDetail?) {

    result?.recyclerTitle(view)

}

fun navigate(view: View, sku: String) {

    Navigation.findNavController(view)
        .navigate(ResultsFragmentDirections.resultsToResultDetail(sku))
}