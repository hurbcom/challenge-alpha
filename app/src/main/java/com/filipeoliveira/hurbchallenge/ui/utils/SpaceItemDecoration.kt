package com.filipeoliveira.hurbchallenge.ui.utils

import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(
    private val spaceTop: Int = 0,
    private val spaceBottom: Int = 0,
    private val spaceStart: Int = 0,
    private val spaceEnd: Int = 0,
    private val extraSpaceBetweenItems: Int = 0
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildLayoutPosition(view)
        val isFirstItem: Boolean = position == 0
        val isLastItem: Boolean = position == parent.adapter?.itemCount?.minus(1) ?: false
        val bottomNavBarHeight = with(TypedValue().also {
            parent.context.theme.resolveAttribute(android.R.attr.actionBarSize, it, true)
        }) {
            TypedValue.complexToDimensionPixelSize(this.data, (parent.context as AppCompatActivity).resources.displayMetrics)
        }

        outRect.left = spaceStart
        outRect.right = spaceEnd

        if (isFirstItem){
            outRect.top = spaceTop
        } else {
            outRect.top = (spaceTop / 2) + extraSpaceBetweenItems
        }

        if (isLastItem){
            outRect.bottom = spaceBottom + bottomNavBarHeight
        } else {
            outRect.bottom = spaceBottom / 2
        }


    }
}