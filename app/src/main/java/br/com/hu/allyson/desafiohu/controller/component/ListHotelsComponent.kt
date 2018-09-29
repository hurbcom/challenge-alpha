package br.com.hu.allyson.desafiohu.controller.component

import android.content.Context
import android.util.Log
import android.widget.LinearLayout
import android.widget.RatingBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import br.com.hu.allyson.desafiohu.controller.interfaces.ComponentFactoryImpl
import br.com.hu.allyson.desafiohu.domain.Hotels
import br.com.hu.allyson.desafiohu.ui.hotels.adapter.HotelsAdapter
import br.com.hu.allyson.desafiohu.util.GridSpacingItemDecoration

class ListHotelsComponent : ComponentFactoryImpl {

    override fun buildComponent(rootview: ConstraintLayout, title: Int?, hotels: List<Hotels>?, id: Int?) {
        val context = rootview.context
        var linearLayout = buildLinearLayout(context)

        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        val title = buildTitleBlock(context, title!!, params)
        linearLayout.addView(title)
        linearLayout.id = id!!

        val rvParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        val rv_hotels = RecyclerView(context)
        rv_hotels.layoutParams = rvParams
        val newsAdapter = HotelsAdapter(context, hotels!!)
        rv_hotels.adapter = newsAdapter
        newsAdapter.notifyDataSetChanged()

        val mLayoutManager = androidx.recyclerview.widget.GridLayoutManager(context, 2)
        rv_hotels.layoutManager = mLayoutManager
        rv_hotels.addItemDecoration(GridSpacingItemDecoration(2, 16, true))
        rv_hotels.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rv_hotels.isNestedScrollingEnabled = false

        linearLayout.addView(rv_hotels)

        rootview.addView(linearLayout)

        ConstraintSet().apply {
            clone(rootview)
            connect(linearLayout.id, ConstraintSet.START, rootview.id, ConstraintSet.START)
            connect(linearLayout.id, ConstraintSet.END, rootview.id, ConstraintSet.END)
            connect(linearLayout.id, ConstraintSet.TOP, id - 1, ConstraintSet.BOTTOM)
            applyTo(rootview)
        }

        Log.e("Ok", hotels[0].name)
    }

    private fun buildTitleBlock(context: Context, title: Int, paramsTitles: LinearLayout.LayoutParams): RatingBar {
        var rating = RatingBar(context)
        rating.layoutParams = paramsTitles
        rating.numStars = 5
        rating.rating =  title.toFloat()
        rating.setIsIndicator(true)

        return rating
    }


    private fun buildLinearLayout(context: Context): LinearLayout {
        val params = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(0,
            32,
            0,
            32)
        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.layoutParams = params

        return linearLayout

    }

}