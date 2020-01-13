package com.ayodkay.alpha.challenge.model

data class HotelModel(
    val name:String,
    val price: String,
    val images:ArrayList<ArrayList<String>>,
    val smallDescription:String,
    val address:String,
    val city :String,
    val state:String,
    val country:String,
    val star:String,
    val hu_free_cancellation:Boolean
)