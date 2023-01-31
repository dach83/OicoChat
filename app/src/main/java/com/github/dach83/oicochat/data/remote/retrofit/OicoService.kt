package com.github.dach83.oicochat.data.remote.retrofit

import com.github.dach83.oicochat.data.remote.retrofit.dto.QuoteDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OicoService {

    @GET
    suspend fun quotes(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): List<QuoteDto>

    companion object {
        const val BASE_URL = "https://oico.app/test/"
    }
}
