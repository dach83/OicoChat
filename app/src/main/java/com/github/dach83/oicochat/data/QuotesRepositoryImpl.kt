package com.github.dach83.oicochat.data

import com.github.dach83.oicochat.data.remote.RemoteQuotesDataSource
import com.github.dach83.oicochat.domain.QuotesRepository
import com.github.dach83.oicochat.domain.model.QuotesPage

class QuotesRepositoryImpl(
    private val remoteSource: RemoteQuotesDataSource
) : QuotesRepository {

    override suspend fun quotesPage(pageNum: Int, pageSize: Int) = QuotesPage(
        pageNum = pageNum,
        quotes = remoteSource.quotes(pageSize, offset(pageNum, pageSize))
    )

    private fun offset(pageNum: Int, pageSize: Int) = (pageNum - 1) * pageSize
}
