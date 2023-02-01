package com.github.dach83.oicochat.fakes

import com.github.dach83.oicochat.domain.QuotesRepository
import com.github.dach83.oicochat.domain.model.Details
import com.github.dach83.oicochat.domain.model.QuotesPage
import com.github.dach83.oicochat.models.testDetails
import com.github.dach83.oicochat.models.testQuotes

class FakeQuotesRepository : QuotesRepository {

    private var error: Exception? = null

    var quotesPageCallCounter: Int = 0
        private set

    var detailsCallCounter: Int = 0
        private set

    override suspend fun quotesPage(pageNum: Int, pageSize: Int): QuotesPage {
        quotesPageCallCounter++
        error?.let { throw it }
        return QuotesPage(
            pageNum = pageNum,
            quotes = testQuotes
        )
    }

    override suspend fun details(quoteId: Int): Details {
        detailsCallCounter++
        error?.let { throw it }
        return testDetails
    }

    fun successMode() {
        error = null
    }

    fun errorMode(cause: Exception) {
        error = cause
    }
}
