package app.recrutamento.android.challengealpha.model

import app.recrutamento.android.challengealpha.R
import app.recrutamento.android.challengealpha.model.enums.AmenityEnum

class AmenityIcon {

    companion object {

        fun getIcon(amenity: AmenityEnum):Int{

            return when(amenity){
                AmenityEnum.AMENITY_COMODIDADES -> R.drawable.ic_swimmer
                AmenityEnum.AMENITY_RECEPCAO -> R.drawable.ic_reception
                AmenityEnum.AMENITY_COMIDA_BEBIDA -> R.drawable.ic_restaurant
                AmenityEnum.AMENITY_COMODIDADE_NEGOCIO -> R.drawable.ic_bussiness_room
                AmenityEnum.AMENITY_SERVICOS -> R.drawable.ic_cleaning_services
                AmenityEnum.AMENITY_TRANSPORTE -> R.drawable.ic_bus
                AmenityEnum.AMENITY_ENTRETENIMENTO -> R.drawable.ic_family
                AmenityEnum.AMENITY_ATIVIDADES -> R.drawable.ic_activity
                AmenityEnum.AMENITY_LOJAS -> R.drawable.ic_store
                AmenityEnum.AMENITY_DIVERSOS -> R.drawable.ic_several
                AmenityEnum.AMENITY_AREA_COMUM -> R.drawable.ic_commom_area
                else -> R.drawable.ic_general_amenity
            }
        }
    }

}