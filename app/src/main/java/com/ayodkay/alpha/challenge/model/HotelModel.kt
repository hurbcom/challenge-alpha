package com.ayodkay.alpha.challenge.model

data class HotelModel(
    val name:String,
    val price: String,
    val images:ArrayList<ArrayList<String>>,
    val amenities:ArrayList<ArrayList<Amenities>>? = null,
    val descriptions: Descriptions,
    val address: Address,
    val star:String,
    val hu_free_cancellation:Boolean
)