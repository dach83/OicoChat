package com.github.dach83.oicochat.data.remote.retrofit

import com.github.dach83.oicochat.data.remote.RemoteQuotesDataSource
import com.github.dach83.oicochat.data.remote.retrofit.mapper.toQuote
import com.github.dach83.oicochat.domain.model.Quote

class RetrofitQuotesDataSource(
    private val oicoService: OicoService
) : RemoteQuotesDataSource {

    override suspend fun quotes(limit: Int, offset: Int): List<Quote> =
        oicoService.quotes(limit, offset).map { quoteDto ->
            quoteDto.toQuote()
        }
}
