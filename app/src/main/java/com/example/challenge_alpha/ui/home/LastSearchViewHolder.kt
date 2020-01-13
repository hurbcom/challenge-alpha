package com.example.challenge_alpha.ui.home

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.challenge_alpha.R
import com.example.challenge_alpha.data.ResultDetailRelation
import java.text.NumberFormat
import java.util.*

class LastSearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    private val cardViewDisplay: CardView = itemView.findViewById(R.id.cardview_display)
    private val imageDisplay: ImageView = itemView.findViewById(R.id.image_display)
    private val constraintDisplay: ConstraintLayout = itemView.findViewById(R.id.constraint_display)
    private val nameDisplay: TextView = itemView.findViewById(R.id.name_display)
    private val starsDisplay: ImageView = itemView.findViewById(R.id.stars_display)
    private val starsTextDisplay: TextView = itemView.findViewById(R.id.starsText_display)
    private val locationDisplay: TextView = itemView.findViewById(R.id.location_display)
    private val priceDisplay: TextView = itemView.findViewById(R.id.price_display)

    private var resultDetail: ResultDetailRelation? = null

    init {
        view.setOnClickListener {

            val navController = findNavController(it)

            val action =
                HomeFragmentDirections.hotelsToResultDetail(resultDetail!!.resultDetail!!.sku)
            navController.navigate(action)

        }

    }


    fun bind(result: ResultDetailRelation?) {

        if (result == null) {

        } else {
            loadData(result)
        }

    }

    private fun loadData(result: ResultDetailRelation) {
        this.resultDetail = result


        nameDisplay.text = result.resultDetail?.name
        locationDisplay.text = itemView.context.resources.getString(
            R.string.location_display,
            result.resultDetail?.address?.city,
            result.resultDetail?.address?.state
        )

        val priceFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        priceFormat.currency = Currency.getInstance(result.resultDetail?.price?.currency?: "BRL")
        val priceResult = priceFormat.format(result.resultDetail?.price?.amount)

        priceDisplay.text = priceResult


        starsTextDisplay.text = if (result.resultDetail?.stars == null) {
            starsDisplay.visibility = View.INVISIBLE
            starsTextDisplay.visibility = View.INVISIBLE
            "5"
        } else result.resultDetail?.stars!!.toInt().toString()


        val imageLoad: String? =
            result.resultDetail?.image ?: result.resultDetailGallery.firstOrNull()?.url
        Glide.with(itemView)
            .load(imageLoad)
            .fitCenter()
            .centerCrop()
            .placeholder(R.drawable.ic_refresh)
            .error(
                Glide.with(cardViewDisplay)
                    .load(result.resultDetailGallery.firstOrNull()?.url)
                    .error(R.drawable.ic_sync_problem)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            p0: GlideException?,
                            p1: Any?,
                            p2: Target<Drawable>?,
                            p3: Boolean
                        ): Boolean {
                            Log.d("glideTest", "$p0")
                            constraintDisplay.visibility = View.INVISIBLE

                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {

                            return false
                        }
                    })
            )
            .fallback(R.drawable.ic_sync_problem)
            .into(imageDisplay)


    }

    companion object {
        fun create(parent: ViewGroup): LastSearchViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.viewholder_horizontal, parent, false)
            return LastSearchViewHolder(view)
        }
    }
}