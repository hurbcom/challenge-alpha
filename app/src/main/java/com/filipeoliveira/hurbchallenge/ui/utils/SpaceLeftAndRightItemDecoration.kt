package com.filipeoliveira.hurbchallenge.ui.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceLeftAndRightItemDecoration(
    private val spaceLeft: Int = 0,
    private val spaceRight: Int = 0,
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

       if (isFirstItem){
           outRect.left = spaceLeft
       } else {
           outRect.left = spaceLeft / 2
       }

        if (isLastItem){
            outRect.right = spaceRight
        } else {
            outRect.right = spaceRight / 2
        }
    }
}