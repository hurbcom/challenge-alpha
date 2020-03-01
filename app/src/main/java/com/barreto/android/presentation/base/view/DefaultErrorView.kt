package com.barreto.android.presentation.base.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.barreto.android.R

class DefaultErrorView : FrameLayout {

    private val errorImage by lazy { findViewById<ImageView>(R.id.errorImage) }
    private val errorTitleText by lazy { findViewById<TextView>(R.id.errorTitleText) }
    private val errorText by lazy { findViewById<TextView>(R.id.errorText) }
    private val refreshButton by lazy { findViewById<Button>(R.id.refreshButton) }

    constructor(context: Context) : super(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        inflate(context, R.layout.layout_error_default, this)
    }

    fun setErrorImage(resourceId: Int) {
        errorImage.setImageResource(resourceId)
    }

    fun setErrorTitle(title: String) {
        errorTitleText.text = title
    }

    fun setErrorTitle(titleId: Int) {
        setErrorTitle(context.getString(titleId))
    }

    fun setErrorText(text: String) {
        errorText.text = text
        errorText.visibility = if (text.isBlank()) View.GONE else View.VISIBLE
    }

    fun setErrorText(textId: Int = 0) {
        setErrorText(textId.takeIf { it != 0 }?.let { context.getString(textId) } ?: "")
    }

    fun setErrorButtonAction(action: (() -> Unit)? = null) {

        if(action != null) {
            refreshButton.setOnClickListener { action.invoke() }
            refreshButton.visibility = View.VISIBLE
        } else {
            refreshButton.setOnClickListener(null)
            refreshButton.visibility = View.GONE
        }
    }
}