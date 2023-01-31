package com.github.dach83.oicochat.data

import com.github.dach83.oicochat.domain.QuotesRepository
import com.github.dach83.oicochat.domain.model.QuotesPage

class QuotesRepositoryImpl : QuotesRepository {

    override suspend fun quotesPage(pageNum: Int, pageSize: Int): QuotesPage {
        return QuotesPage(1, emptyList()) // todo
    }
}
