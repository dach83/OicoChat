package com.github.dach83.oicochat.data.remote.retrofit.mapper

import com.github.dach83.oicochat.data.remote.retrofit.dto.QuoteDto
import com.github.dach83.oicochat.domain.model.Quote
import com.github.dach83.oicochat.domain.model.QuoteAlign

fun QuoteDto.toQuote() = Quote(
    id = id,
    text = text.orEmpty(),
    align = if (createdBy == 0) {
        QuoteAlign.LEFT
    } else {
        QuoteAlign.RIGHT
    }
)
