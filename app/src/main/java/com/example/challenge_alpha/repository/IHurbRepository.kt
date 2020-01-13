package com.example.challenge_alpha.repository

import androidx.lifecycle.LiveData
import com.example.challenge_alpha.api.HurbSuggestions
import com.example.challenge_alpha.api.Result

interface IHurbRepository {
    fun search(queryString: String)
    fun suggestionSearch(suggestion: String): LiveData<Result<HurbSuggestions>>
}