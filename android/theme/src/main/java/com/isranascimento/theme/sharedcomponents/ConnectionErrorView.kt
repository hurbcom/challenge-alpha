package com.isranascimento.theme.sharedcomponents

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.VisibleForTesting
import androidx.constraintlayout.widget.ConstraintLayout
import com.isranascimento.theme.databinding.ConnectionErrorViewBinding

class ConnectionErrorView(context: Context, attrs: AttributeSet?): ConstraintLayout(context, attrs) {
    @VisibleForTesting val binding = ConnectionErrorViewBinding.inflate(
        LayoutInflater.from(context),
        this
    )

    fun setOnTryAgainClickListener(listener: ConnectionErrorListener) {
        binding.tryAgainButton.setOnClickListener {
            listener.onTryAgainClick()
        }
    }

    fun interface ConnectionErrorListener {
        fun onTryAgainClick()
    }
}