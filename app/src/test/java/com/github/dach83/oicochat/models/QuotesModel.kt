package com.github.dach83.oicochat.models

import com.github.dach83.oicochat.domain.model.Quote
import com.github.dach83.oicochat.domain.model.QuoteAlign

val testQuote1 = Quote(
    id = 1,
    text = "Честно говоря, моя дорогая, мне наплевать",
    align = QuoteAlign.LEFT
)

val testQuote2 = Quote(
    id = 2,
    text = "Я собираюсь сделать ему предложение, от которого он не сможет отказаться",
    align = QuoteAlign.RIGHT
)

val testQuotes = listOf(
    testQuote1,
    testQuote2
)
