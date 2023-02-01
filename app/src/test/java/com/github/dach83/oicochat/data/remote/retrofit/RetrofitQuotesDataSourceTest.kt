package com.github.dach83.oicochat.data.remote.retrofit

import com.github.dach83.oicochat.models.quotesCorrectJson
import com.github.dach83.oicochat.models.quotesInvalidJson
import com.github.dach83.oicochat.models.testQuotes
import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@OptIn(ExperimentalCoroutinesApi::class)
class RetrofitQuotesDataSourceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var service: OicoService
    private val client = OkHttpClient.Builder().build()
    private val moshi: Moshi = Moshi.Builder().build()

    @Before
    fun createServer() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/")) // setting a dummy url
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()
            .create(OicoService::class.java)
    }

    @After
    fun shutdownServer() {
        mockWebServer.shutdown()
    }

    @Test
    fun `correct response returns correct quotes`() = runTest {
        // arrange
        val response = MockResponse()
            .setBody(quotesCorrectJson)
            .setResponseCode(200)
        mockWebServer.enqueue(response)
        val sut = RetrofitQuotesDataSource(service)
        val expected = testQuotes

        // act
        val actual = sut.quotes(limit = 2, offset = 1)

        // assert
        assertThat(actual).isEqualTo(expected)
    }

    @Test(expected = Exception::class)
    fun `invalid response throw Exception`() = runTest {
        // arrange
        val response = MockResponse()
            .setBody(quotesInvalidJson)
            .setResponseCode(200)
        mockWebServer.enqueue(response)
        val sut = RetrofitQuotesDataSource(service)

        // act
        sut.quotes(limit = 2, offset = 1)
    }

    @Test(expected = Exception::class)
    fun `http error throw Exception`() = runTest {
        // arrange
        val response = MockResponse()
            .setBody(quotesCorrectJson)
            .setResponseCode(400)
        mockWebServer.enqueue(response)
        val sut = RetrofitQuotesDataSource(service)

        // act
        sut.quotes(limit = 2, offset = 1)
    }
}
