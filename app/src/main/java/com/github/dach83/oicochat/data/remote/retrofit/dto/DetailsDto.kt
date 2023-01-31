package com.github.dach83.oicochat.data.remote.retrofit.dto

data class DetailsDto(
    val id: Int,
    val createdAt: String?,
    val createdBy: Int?,
    val text: String?,
    val tagList: List<String>?,
    val colors: List<String>?
)
