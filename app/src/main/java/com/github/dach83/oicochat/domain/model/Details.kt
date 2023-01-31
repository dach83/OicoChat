package com.github.dach83.oicochat.domain.model

data class Details(
    val id: Int,
    val text: String,
    val date: String,
    val tags: List<String>,
    val colors: List<TagColor>
)
