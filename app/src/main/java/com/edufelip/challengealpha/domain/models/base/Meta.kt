package com.edufelip.challengealpha.domain.models.base

data class Meta(
    val limit: Long,
    val next: String?,
    val offset: Long,
    val total: Long
)