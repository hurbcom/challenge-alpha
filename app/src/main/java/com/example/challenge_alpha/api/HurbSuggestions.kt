package com.example.challenge_alpha.api

import com.example.challenge_alpha.model.*
import com.google.gson.annotations.SerializedName

class HurbSuggestions (
    @SerializedName("suggestions") val suggestions: List<Suggestions> = emptyList()
)