package com.example.challenge_alpha.utils

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.challenge_alpha.model.ResultDetail

fun Context.toastLong(message: String) {

    Toast.makeText(
        this,
        message,
        Toast.LENGTH_LONG
    ).show()

}

fun ResultDetail.recyclerTitle(view: TextView) {

    view.text = this.recyclerTitle.let { isTitle ->
        if (isTitle) {
            view.visibility = View.VISIBLE
            if (this.stars?.toInt() != null) {
                "${this.stars.toInt()} Estrelas"
            } else {
                "Pacotes"
            }

        } else {
            view.visibility = View.GONE
            "Estrelas"
        }
    }
}