package com.github.dach83.oicochat.data.remote

import com.github.dach83.oicochat.domain.model.Quote

interface RemoteQuotesDataSource {

    suspend fun quotes(limit: Int, offset: Int): List<Quote>
}
