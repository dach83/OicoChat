package com.github.dach83.oicochat.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.dach83.oicochat.domain.QuotesRepository
import com.github.dach83.oicochat.domain.model.Quote

class QuotesPagingSource(
    private val repository: QuotesRepository
) : PagingSource<Int, Quote>() {

    override fun getRefreshKey(state: PagingState<Int, Quote>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Quote> = try {
        val nextPageNum = params.key ?: 1
        val quotesPage = repository.quotesPage(nextPageNum, params.loadSize)
        LoadResult.Page(
            data = quotesPage.quotes,
            prevKey = quotesPage.pageNum.minus(1).coerceAtLeast(1),
            nextKey = if (quotesPage.quotes.isNotEmpty()) quotesPage.pageNum.plus(1) else null
        )
    } catch (cause: Exception) {
        LoadResult.Error(cause)
    }
}
