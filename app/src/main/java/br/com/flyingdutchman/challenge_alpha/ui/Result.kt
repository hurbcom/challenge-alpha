package br.com.flyingdutchman.challenge_alpha.ui

import br.com.flyingdutchman.challenge_alpha.data.model.ImageUrl

data class Result(
    val id: String,
    var name: String,
    var url: String,
    var description: String,
    var shortDescription: String,
    var gallery: List<ImageUrl>,
    var freeCancelation: Boolean,
    var currentPrice: String,
    var oldPrice: String,
    var rating: Int,
    var city:String
)