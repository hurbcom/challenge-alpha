package com.example.challenge_alpha.db

import androidx.room.Embedded
import androidx.room.Relation
import com.example.challenge_alpha.model.ResultDetail
import com.example.challenge_alpha.model.ResultDetailAmenities
import com.example.challenge_alpha.model.ResultDetailGallery

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
}