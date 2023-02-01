package com.github.dach83.oicochat.fakes

import com.github.dach83.oicochat.data.remote.RemoteQuotesDataSource
import com.github.dach83.oicochat.domain.model.Details
import com.github.dach83.oicochat.domain.model.Quote
import com.github.dach83.oicochat.models.testDetails
import com.github.dach83.oicochat.models.testQuotes

class FakeRemoteQuotesDataSource : RemoteQuotesDataSource {

    var quotesWasCall: Boolean = false
        private set

    var quotesLimit: Int = -1
        private set

    var quotesOffset: Int = -1
        private set

    var detailsWasCalled: Boolean = false
        private set

    var detailsQuoteId: Int = -1
        private set

    override suspend fun quotes(limit: Int, offset: Int): List<Quote> {
        quotesWasCall = true
        quotesLimit = limit
        quotesOffset = offset
        return testQuotes
    }

    override suspend fun details(quoteId: Int): Details {
        detailsWasCalled = true
        detailsQuoteId = quoteId
        return testDetails
    }
}
