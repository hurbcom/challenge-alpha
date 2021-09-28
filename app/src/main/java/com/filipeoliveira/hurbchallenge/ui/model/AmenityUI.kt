package com.filipeoliveira.hurbchallenge.ui.model

import java.io.Serializable

data class AmenityUI(
    val name: String
): Serializable {

    companion object {
        const val AMENITY_BAR = "Bar"
        const val AMENITY_WHEELCHAIR = "Accessibilidade para Cadeira de Rodas"
        const val AMENITY_TV = "Aparelho TV"
        const val AMENITY_TOILET = "Banheiro"
        const val AMENITY_PARKING = "Estacionamento Gratuito"
        const val AMENITY_WIFI = "Internet WiFi"
        const val AMENITY_POOL = "Piscina"
        const val AMENITY_RECEPTION = "Recepção 24 horas"
        const val AMENITY_RESTAURANT = "Restaurante"
        const val AMENITY_GYM = "Academia de ginástica gratuita"
    }
}
