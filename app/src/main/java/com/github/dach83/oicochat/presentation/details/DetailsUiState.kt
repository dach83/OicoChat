package com.github.dach83.oicochat.presentation.details

import com.github.dach83.oicochat.domain.model.Details

sealed class DetailsUiState {

    object Loading : DetailsUiState()

    data class Loaded(val details: Details) : DetailsUiState()

    data class Error(val cause: Throwable) : DetailsUiState()
}
