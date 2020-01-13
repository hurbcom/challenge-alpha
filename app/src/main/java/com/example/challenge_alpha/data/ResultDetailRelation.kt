package com.example.challenge_alpha.data

import androidx.room.Embedded
import androidx.room.Relation
import com.example.challenge_alpha.model.*

/**
 * A classe [ResultDetailRelation] cria relações do banco para facilitar a busca de dados do Room.
 * A classe foi criada porque o Room não suporta todos os tipos de dados necessários para armazenar
 * os dados enviados pelo servidor em uma busca.
 *
 */

class ResultDetailRelation {

    @Embedded
    var resultDetail: ResultDetail? = null

    @Relation(
        parentColumn = "sku",
        entityColumn = "sku"
    )
    var resultDetailAmenities: List<ResultDetailAmenities> = emptyList()

    @Relation(
        parentColumn = "sku",
        entityColumn = "sku"
    )
    var resultDetailGallery: List<ResultDetailGallery> = emptyList()

    @Relation(
        parentColumn = "sku",
        entityColumn = "sku"
    )
    var resultDetailTaxes: List<ResultDetailTaxes> = emptyList()


}