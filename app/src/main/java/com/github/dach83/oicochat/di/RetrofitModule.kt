package com.github.dach83.oicochat.di

import com.github.dach83.oicochat.BuildConfig
import com.github.dach83.oicochat.data.remote.retrofit.OicoService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val retrofitModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            )
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(OicoService.BASE_URL)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single<OicoService> {
        get<Retrofit>().create(OicoService::class.java)
    }
}
