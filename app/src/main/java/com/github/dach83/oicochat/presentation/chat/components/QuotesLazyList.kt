package com.github.dach83.oicochat.presentation.chat.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.github.dach83.oicochat.R
import com.github.dach83.oicochat.domain.model.Quote

@Composable
fun QuotesLazyList(
    quotes: LazyPagingItems<Quote>,
    onQuoteClick: (Quote) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(quotes) { quote ->
            quote?.let {
                QuoteCard(quote, onQuoteClick)
            }
        }
        when (quotes.loadState.append) {
            LoadState.Loading -> loadingItem()
            is LoadState.Error -> errorItem(quotes::retry)
            is LoadState.NotLoading -> Unit
        }
    }
}

private fun LazyListScope.loadingItem() = item {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        CircularProgressIndicator()
    }
}

private fun LazyListScope.errorItem(
    onRetryClick: () -> Unit
) = item {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = onRetryClick) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}
