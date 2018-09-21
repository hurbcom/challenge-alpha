package br.com.hu.allyson.desafiohu.domain

data class Meta(
    val count: Int,
    val offset: Int,
    val query: String,
    val warning: String,
    val countWithAvailabilityInPage: Int,
    val responseTime: ResponseTime,
    val countHotel: Int,
    val countPackage: Int
)