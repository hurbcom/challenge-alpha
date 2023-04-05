package com.example.starwars.data.model

import com.google.gson.annotations.SerializedName

data class MoviesPage(
    @SerializedName("next")
    var nextPage: String?,
    @SerializedName("previous")
    var previousPage: String?,
    @SerializedName("results")
    val movieList: List<MoviesRemote>
)