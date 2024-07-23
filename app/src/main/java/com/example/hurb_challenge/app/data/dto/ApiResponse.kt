package com.example.hurb_challenge.app.data.dto

data class ApiResponse<out Node>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Node>
)
