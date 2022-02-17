package com.isranascimento.theme.sharedcomponents

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.isranascimento.theme.databinding.ConnectionErrorViewBinding

class ConnectionErrorView(context: Context, attrs: AttributeSet?): ConstraintLayout(context, attrs) {
    val binding = ConnectionErrorViewBinding.inflate(
        LayoutInflater.from(context),
        this
    )

    fun setOnTryAgainClickListener(listener: ConnectionErrorListener) {
        binding.tryAgainButton.setOnClickListener {
            listener.onTryAgainClick()
        }
    }

    interface ConnectionErrorListener {
        fun onTryAgainClick()
    }
}