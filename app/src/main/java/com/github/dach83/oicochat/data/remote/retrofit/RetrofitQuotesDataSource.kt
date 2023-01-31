package com.github.dach83.oicochat.data.remote.retrofit

import com.github.dach83.oicochat.R
import com.github.dach83.oicochat.data.remote.RemoteQuotesDataSource
import com.github.dach83.oicochat.data.remote.retrofit.dto.QuotesDto
import com.github.dach83.oicochat.data.remote.retrofit.mapper.toQuotes
import com.github.dach83.oicochat.domain.exception.AppException
import com.github.dach83.oicochat.domain.model.Quote
import com.squareup.moshi.JsonDataException
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

class RetrofitQuotesDataSource(
    private val oicoService: OicoService
) : RemoteQuotesDataSource {

    override suspend fun quotes(limit: Int, offset: Int): List<Quote> = try {
        val response = oicoService.quotes(limit, offset)
        response.toQuotes()
    } catch (cause: JsonDataException) {
        throw AppException(R.string.unknown_server_response, cause)
    } catch (cause: UnknownHostException) {
        throw AppException(R.string.no_internet)
    }

    private fun Response<QuotesDto>.toQuotes(): List<Quote> = if (isSuccessful) {
        val quotesDto = body()
        quotesDto.toQuotes()
    } else {
        // todo: various http error codes should be handled here
        throw AppException(R.string.http_error, HttpException(this))
    }
}
