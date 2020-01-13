package com.example.challenge_alpha.data

import androidx.room.Embedded
import androidx.room.Relation
import com.example.challenge_alpha.model.ResultDetail
import com.example.challenge_alpha.model.ResultDetailAmenities
import com.example.challenge_alpha.model.ResultDetailGallery
import com.example.challenge_alpha.model.ResultDetailTaxes

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