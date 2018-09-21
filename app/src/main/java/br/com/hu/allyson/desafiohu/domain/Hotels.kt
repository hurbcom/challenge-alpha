package br.com.hu.allyson.desafiohu.domain

data class Hotels(
    val sku: String,
    val isHotel: Boolean,
    val isPackage: Boolean,
    val amenities: List<Amenity>,
    val id: String,
    val price: Price,
    val image: String,
    val name: String,
    val stars: Int,
    val address: Address
)