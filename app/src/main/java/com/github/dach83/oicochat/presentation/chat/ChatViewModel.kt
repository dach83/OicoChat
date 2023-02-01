package com.github.dach83.oicochat.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.github.dach83.oicochat.data.QuotesPagingSource
import com.github.dach83.oicochat.domain.QuotesRepository

class ChatViewModel(
    private val repository: QuotesRepository
) : ViewModel() {

    // no more than 10 messages from the server at a time
    private val pagingConfig = PagingConfig(
        pageSize = DEFAULT_PAGE_SIZE,
        initialLoadSize = DEFAULT_PAGE_SIZE
    )

    val quotesPagingFlow = Pager(pagingConfig) {
        QuotesPagingSource(repository)
    }.flow.cachedIn(viewModelScope)

    companion object {
        private const val DEFAULT_PAGE_SIZE = 10
    }
}
