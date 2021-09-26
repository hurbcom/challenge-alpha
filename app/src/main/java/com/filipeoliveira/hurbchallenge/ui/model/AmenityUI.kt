package com.filipeoliveira.hurbchallenge.ui.model

data class AmenityUI(
    val term: String,
    val name: String
) {

    companion object {
        const val AMENITY_BAR = "amenity_bar|1"
        const val AMENITY_WHEELCHAIR = "amenity_accessibilidade_para_cadeira_de_rodas|1"
        const val AMENITY_TV = "amenity_aparelho_tv|1"
        const val AMENITY_TOILET = "amenity_banheiro|1"
        const val AMENITY_PARKING = "amenity_estacionamento|1"
        const val AMENITY_WIFI = "amenity_internet_wifi|1"
        const val AMENITY_POOL = "amenity_piscina|1"
        const val AMENITY_RECEPTION = "amenity_recepcao_24_horas|1"
        const val AMENITY_RESTAURANT = "amenity_restaurante|1"
        const val AMENITY_GYM = "amenity_academia_de_ginastica_gratuita|1"
    }
}
