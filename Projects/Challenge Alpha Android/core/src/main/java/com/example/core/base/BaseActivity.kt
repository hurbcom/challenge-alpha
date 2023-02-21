package com.example.core.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity()  {
    abstract fun hasToolbar(show: Boolean)
}