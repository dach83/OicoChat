package com.github.dach83.oicochat.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.dach83.oicochat.domain.QuotesRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: QuotesRepository
) : ViewModel() {

    var uiState: DetailsUiState by mutableStateOf(DetailsUiState.Loading)
        private set

    private var loadingJob: Job? = null

    fun loadDetails(id: Int) {
        if (idNotChanged(id)) return
        uiState = DetailsUiState.Loading
        loadingJob?.cancel()
        loadingJob = viewModelScope.launch {
            kotlin.runCatching {
                repository.details(id)
            }.onSuccess { detail ->
                uiState = DetailsUiState.Loaded(detail)
            }.onFailure { cause ->
                uiState = DetailsUiState.Error(cause)
            }
        }
    }

    private fun idNotChanged(id: Int): Boolean {
        val state = uiState
        return (state is DetailsUiState.Loaded && state.details.id == id)
    }
}
