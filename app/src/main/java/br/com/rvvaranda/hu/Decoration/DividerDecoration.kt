package br.com.rvvaranda.hu.Decoration

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class DividerDecoration(internal var _divider: Drawable) : RecyclerView.ItemDecoration() {

   override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        if (parent.getChildAdapterPosition(view) === 0) {
            return
        }

        outRect.top = _divider.intrinsicHeight
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        val dividerLeft = parent.getPaddingLeft()
        val dividerRight = parent.getWidth() - parent.getPaddingRight()

        val childCount = parent.getChildCount()
        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)

            val params = child.getLayoutParams() as RecyclerView.LayoutParams

            val dividerTop = child.getBottom() + params.bottomMargin
            val dividerBottom = dividerTop + _divider.intrinsicHeight

            _divider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom)
            _divider.draw(c)
        }
    }


}