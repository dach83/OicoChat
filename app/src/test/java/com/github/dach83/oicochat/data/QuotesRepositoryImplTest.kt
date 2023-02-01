package com.github.dach83.oicochat.data

import com.github.dach83.oicochat.fakes.FakeRemoteQuotesDataSource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class QuotesRepositoryImplTest {

    private val fakeRemoteDataSource = FakeRemoteQuotesDataSource()

    @Test
    fun `quotesPage calls data source with correct limit and offset`() = runTest {
        // arrange
        val sut = QuotesRepositoryImpl(fakeRemoteDataSource)

        // act
        sut.quotesPage(pageNum = 2, pageSize = 10)

        // assert
        assertThat(fakeRemoteDataSource.quotesWasCall).isTrue()
        assertThat(fakeRemoteDataSource.quotesLimit).isEqualTo(10)
        assertThat(fakeRemoteDataSource.quotesOffset).isEqualTo(11)
    }

    @Test
    fun `details calls data source with correct quoteId`() = runTest {
        // arrange
        val sut = QuotesRepositoryImpl(fakeRemoteDataSource)

        // act
        sut.details(quoteId = 7)

        // assert
        assertThat(fakeRemoteDataSource.detailsWasCalled).isTrue()
        assertThat(fakeRemoteDataSource.detailsQuoteId).isEqualTo(7)
    }
}
