package com.github.dach83.oicochat.presentation.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.github.dach83.oicochat.presentation.chat.components.ErrorMessageAndRefreshButton
import com.github.dach83.oicochat.presentation.chat.components.LoadingIndicator
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun DetailsScreen(
    quoteId: Int,
    viewModel: DetailsViewModel = koinViewModel()
) {
    LaunchedEffect(key1 = quoteId) {
        viewModel.loadDetails(quoteId)
    }

    when (val uiState = viewModel.uiState) {
        DetailsUiState.Loading -> LoadingIndicator()
        is DetailsUiState.Loaded -> DetailsColumn(uiState.details)
        is DetailsUiState.Error -> ErrorMessageAndRefreshButton(
            cause = uiState.cause,
            onRefreshClick = { viewModel.loadDetails(quoteId) }
        )
    }
}
