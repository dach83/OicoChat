package com.github.dach83.oicochat.data.remote.retrofit

import com.github.dach83.oicochat.R
import com.github.dach83.oicochat.data.remote.RemoteQuotesDataSource
import com.github.dach83.oicochat.data.remote.retrofit.dto.DetailsDto
import com.github.dach83.oicochat.data.remote.retrofit.dto.QuotesDto
import com.github.dach83.oicochat.data.remote.retrofit.mapper.toDetails
import com.github.dach83.oicochat.data.remote.retrofit.mapper.toQuotes
import com.github.dach83.oicochat.domain.exception.AppException
import com.github.dach83.oicochat.domain.model.Details
import com.github.dach83.oicochat.domain.model.Quote
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class RetrofitQuotesDataSource(
    private val oicoService: OicoService
) : RemoteQuotesDataSource {

    override suspend fun quotes(limit: Int, offset: Int): List<Quote> = try {
        val response = oicoService.quotes(limit, offset)
        response.toQuotes()
    } catch (cause: Throwable) {
        handleException(cause)
    }

    override suspend fun details(quoteId: Int): Details = try {
        val response = oicoService.details(quoteId)
        response.toDetails()
    } catch (cause: Throwable) {
        handleException(cause)
    }

    private fun Response<QuotesDto>.toQuotes(): List<Quote> = if (isSuccessful) {
        val quotesDto = body()
        quotesDto.toQuotes()
    } else {
        handleHttpError(response = this)
    }

    private fun Response<DetailsDto>.toDetails(): Details = if (isSuccessful) {
        val detailsDto = body()
        detailsDto?.toDetails() ?: throw AppException(R.string.unknown_server_response)
    } else {
        handleHttpError(response = this)
    }

    // todo: exceptions should be handled here
    private fun handleException(cause: Throwable): Nothing = when (cause) {
        is UnknownHostException -> throw AppException(R.string.no_internet)
        is SocketTimeoutException -> throw AppException(R.string.no_internet)
        else -> throw cause
    }

    // todo: http error codes should be handled here
    private fun handleHttpError(response: Response<*>): Nothing {
        throw AppException(R.string.http_error, HttpException(response))
    }
}
