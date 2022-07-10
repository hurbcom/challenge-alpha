package com.edufelip.challengealpha.presentation.base.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class SpaceTopAndBottomItemDecoration(
    private val topSpace: Int, private val bottomSpace: Int = topSpace
) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        val itemPosition = parent.getChildAdapterPosition(view)
        val itemCount = state.itemCount
        if (itemCount > 0 && itemPosition == 0) {
            outRect.top = topSpace
        } else if (itemCount > 0 && itemPosition == itemCount - 1) {
            outRect.bottom = bottomSpace
        }
    }
}