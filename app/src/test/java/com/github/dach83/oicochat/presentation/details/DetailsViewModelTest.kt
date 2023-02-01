package com.github.dach83.oicochat.presentation.details

import com.github.dach83.bin.rule.CoroutineRule
import com.github.dach83.oicochat.fakes.FakeQuotesRepository
import com.github.dach83.oicochat.models.testDetails
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailsViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    private val fakeRepository = FakeQuotesRepository()

    @Test
    fun `initial state is Loading`() {
        // arrange
        val sut = DetailsViewModel(fakeRepository)

        // assert
        assertThat(sut.uiState).isEqualTo(DetailsUiState.Loading)
    }

    @Test
    fun `successful loading updates state to Loaded`() = runTest {
        // arrange
        val sut = DetailsViewModel(fakeRepository)
        fakeRepository.successMode()

        // act
        sut.loadDetails(testDetails.id)

        // assert
        advanceUntilIdle()
        assertThat(sut.uiState).isEqualTo(DetailsUiState.Loaded(testDetails))
    }

    @Test
    fun `unsuccessful loading updates state to Error`() = runTest {
        // arrange
        val sut = DetailsViewModel(fakeRepository)
        val cause = IllegalStateException()
        fakeRepository.errorMode(cause)

        // act
        sut.loadDetails(testDetails.id)

        // assert
        advanceUntilIdle()
        assertThat(sut.uiState).isEqualTo(DetailsUiState.Error(cause))
    }

    @Test
    fun `change configuration not request same details again`() = runTest {
        // arrange
        val sut = DetailsViewModel(fakeRepository)

        // act
        sut.loadDetails(testDetails.id)
        advanceUntilIdle()
        sut.loadDetails(testDetails.id) // load same quote after device rotate
        advanceUntilIdle()

        // assert
        assertThat(fakeRepository.detailsCallCounter).isEqualTo(1)
    }
}
