package com.github.dach83.oicochat.presentation.chat

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.github.dach83.oicochat.presentation.chat.components.ErrorMessageAndRefreshButton
import com.github.dach83.oicochat.presentation.chat.components.LoadingIndicator
import com.github.dach83.oicochat.presentation.chat.components.QuotesLazyList
import com.github.dach83.oicochat.presentation.destinations.DetailsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.koinViewModel

@RootNavGraph(start = true)
@Destination
@Composable
fun ChatScreen(
    viewModel: ChatViewModel = koinViewModel(),
    navigator: DestinationsNavigator
) {
    val quotes = viewModel.quotesPagingFlow.collectAsLazyPagingItems()
    when (val state = quotes.loadState.refresh) {
        LoadState.Loading -> LoadingIndicator()

        is LoadState.NotLoading -> QuotesLazyList(
            quotes = quotes,
            onQuoteClick = { quote ->
                navigator.navigate(DetailsScreenDestination(quote.id))
            }
        )

        is LoadState.Error -> ErrorMessageAndRefreshButton(
            cause = state.error,
            onRefreshClick = quotes::refresh
        )
    }
}
