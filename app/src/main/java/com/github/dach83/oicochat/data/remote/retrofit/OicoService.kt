package com.github.dach83.oicochat.data.remote.retrofit

import com.github.dach83.oicochat.data.remote.retrofit.dto.DetailsDto
import com.github.dach83.oicochat.data.remote.retrofit.dto.QuotesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface OicoService {

    @GET("test/")
    suspend fun quotes(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<QuotesDto>

    @GET("test/{id}")
    suspend fun details(
        @Path("id") quoteId: Int
    ): Response<DetailsDto>

    companion object {
        const val BASE_URL = "https://oico.app/"
    }
}
