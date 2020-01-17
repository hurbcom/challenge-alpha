package com.example.challenge_alpha.ui.home


import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.challenge_alpha.R
import com.example.challenge_alpha.data.ResultDetailRelation
import com.example.challenge_alpha.model.ResultDetailGallery
import com.example.challenge_alpha.model.Suggestions


@BindingAdapter(
    "home:imageLoading",
    "home:backupImageLoading",
    "home:imageVisibilityOnNameDisplay",
    "home:imageVisibilityOnPriceDisplay",
    "home:imageVisibilityOnLocationDisplay",
    "home:imageVisibilityOnStarsTextDisplay",
    "home:imageVisibilityOnStarsDisplay"
)
fun imageLoading(
    view: ImageView,
    imageUrl: String?,
    backupImageLoading: ResultDetailGallery?,
    nameDisplay: TextView,
    priceDisplay: TextView,
    locationDisplay: TextView,
    starsTextDisplay: TextView,
    starsDisplay: ImageView
) {


    val imageLoad: String? =
        imageUrl ?: backupImageLoading?.url
    Glide.with(view.context)
        .load(imageLoad)
        .fitCenter()
        .centerCrop()
        .placeholder(R.drawable.ic_refresh)
        .error(
            Glide.with(view.context)
                .load(backupImageLoading?.url)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        p0: GlideException?,
                        p1: Any?,
                        p2: Target<Drawable>?,
                        p3: Boolean
                    ): Boolean {


                        nameDisplay.setTextColor(
                            ContextCompat.getColor(
                                nameDisplay.context,
                                R.color.colorPrimary
                            )
                        )

                        priceDisplay.setTextColor(
                            ContextCompat.getColor(
                                priceDisplay.context,
                                R.color.colorPrimary
                            )
                        )
                        locationDisplay.setTextColor(
                            ContextCompat.getColor(
                                locationDisplay.context,
                                R.color.colorPrimary
                            )
                        )
                        starsTextDisplay.setTextColor(
                            ContextCompat.getColor(
                                starsTextDisplay.context,
                                R.color.colorPrimary
                            )
                        )

                        starsDisplay.visibility = View.VISIBLE

                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {

                        starsDisplay.visibility = View.VISIBLE

                        return false
                    }
                })
        ).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(p0: GlideException?, p1: Any?, p2: Target<Drawable>?, p3: Boolean): Boolean {
                return false
            }
            override fun onResourceReady(p0: Drawable?, p1: Any?, p2: Target<Drawable>?, p3: DataSource?, p4: Boolean): Boolean {

                starsDisplay.visibility = View.VISIBLE

                return false
            }
        })
        .into(view)


}


@BindingAdapter("home:recyclerLastSeen")
fun setRecyclerViewLastSeen(recyclerView: RecyclerView, data: List<ResultDetailRelation>?) {

    if (recyclerView.layoutManager == null) {

        val layoutHorizontal =
            GridLayoutManager(recyclerView.context, 1, LinearLayoutManager.HORIZONTAL, false)

        recyclerView.layoutManager = layoutHorizontal

        recyclerView.adapter = LastSeenAdapter()
        recyclerView.visibility = View.VISIBLE
    }

    (recyclerView.adapter as LastSeenAdapter).submitList(data)

}

@BindingAdapter("home:recyclerLastSearch")
fun setRecyclerViewLastSearch(recyclerView: RecyclerView, data: List<ResultDetailRelation>?) {

    if (recyclerView.layoutManager == null) {

        val layoutHorizontal =
            GridLayoutManager(recyclerView.context, 1, LinearLayoutManager.HORIZONTAL, false)

        recyclerView.layoutManager = layoutHorizontal

        recyclerView.adapter = LastSearchAdapter()
        recyclerView.visibility = View.VISIBLE
    }

    (recyclerView.adapter as LastSearchAdapter).submitList(data)

}


fun navigate(view: View, sku: String) {

    findNavController(view).navigate(HomeFragmentDirections.hotelsToResultDetail(sku))

}

fun navigateSuggestion (view: View, suggestions: Suggestions) {

    val navController = findNavController(view)

    var action = HomeFragmentDirections.hotelsToResults(suggestions.city!!)
    if (suggestions.suggestionType == "hotel") {
        action = HomeFragmentDirections.hotelsToResults(suggestions.text!!)
    }

    navController.navigate(action)

}