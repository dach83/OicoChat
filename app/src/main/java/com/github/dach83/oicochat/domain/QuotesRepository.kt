package com.github.dach83.oicochat.domain

import com.github.dach83.oicochat.domain.model.QuotesPage

interface QuotesRepository {

    suspend fun quotesPage(pageNum: Int, pageSize: Int): QuotesPage
}
