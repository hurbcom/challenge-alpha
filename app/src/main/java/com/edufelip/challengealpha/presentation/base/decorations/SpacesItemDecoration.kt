package com.edufelip.challengealpha.presentation.base.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class SpacesItemDecoration(
    private val space: Int,
    private val insertTop: Boolean = true,
    private val insertLastBottom: Boolean = true
) :
    ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        val position = parent.getChildLayoutPosition(view)
        val isLastItem = position == parent.childCount - 1
        if (!insertLastBottom && isLastItem) {
            outRect.bottom = 0
        } else {
            outRect.bottom = space
        }

        if (insertTop && position == 0) {
            outRect.top = space
        } else {
            outRect.top = 0
        }
    }
}