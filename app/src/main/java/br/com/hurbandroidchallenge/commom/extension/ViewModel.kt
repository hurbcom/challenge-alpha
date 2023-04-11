package br.com.hurbandroidchallenge.commom.extension

import android.app.Application
import androidx.lifecycle.AndroidViewModel

fun AndroidViewModel.getString(res: Int) =
    getApplication<Application>().applicationContext.getString(res)

