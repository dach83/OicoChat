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

    val quotesPagingFlow = Pager(PagingConfig(pageSize = DEFAULT_PAGE_SIZE)) {
        QuotesPagingSource(repository)
    }.flow.cachedIn(viewModelScope)

    companion object {
        private const val DEFAULT_PAGE_SIZE = 10
    }
}
