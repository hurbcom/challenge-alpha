package br.com.hurbandroidchallenge.domain.model

class People (
    val id: Int,
    val name: String,
    val height: String,
    val mass: String,
    val hairColor: String,
    val skinColor: String,
    val birthYear: String,
    val gender: String,
    val homeWorld: String,
    val url: String,
    val films: List<String>,
    val vehicles: List<String>,
    val starships: List<String>,
    val image: String
)