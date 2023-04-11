package com.example.challengealphaandroid.common

open class BaseModel(
    open val name: String,
    var isFavorite: Boolean = false,
    var lastClickedTime: Long = 0
){
    var recentlySeen: Boolean = false
}