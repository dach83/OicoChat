package com.github.dach83.oicochat.data.remote.retrofit.mapper

import com.github.dach83.oicochat.data.remote.retrofit.dto.QuotesDto
import com.github.dach83.oicochat.domain.model.Quote

fun QuotesDto?.toQuotes(): List<Quote> = this?.map { it.toQuote() } ?: emptyList()
