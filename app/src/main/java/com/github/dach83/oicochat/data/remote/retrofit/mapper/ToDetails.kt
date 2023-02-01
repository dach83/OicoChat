package com.github.dach83.oicochat.data.remote.retrofit.mapper

import com.github.dach83.oicochat.data.remote.retrofit.dto.DetailsDto
import com.github.dach83.oicochat.domain.model.Details

fun DetailsDto.toDetails() = Details(
    id = id,
    text = text.orEmpty(),
    createdAt = createdAt.orEmpty(),
    tags = tagList?.toList() ?: emptyList(),
    colors = colors?.map { it.toTagColor() } ?: emptyList()
)
