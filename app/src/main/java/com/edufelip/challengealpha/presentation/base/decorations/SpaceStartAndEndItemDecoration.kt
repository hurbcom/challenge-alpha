package com.edufelip.challengealpha.presentation.base.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class SpaceStartAndEndItemDecoration(
    private val space: Int, private val forceAll: Boolean = false
): ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        if (forceAll || parent.getChildLayoutPosition(view) == 0) {
            outRect.left = space
            outRect.right = space
        } else {
            outRect.right = space
        }
    }
}