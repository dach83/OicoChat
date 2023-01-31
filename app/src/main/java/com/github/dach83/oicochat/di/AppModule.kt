package com.github.dach83.oicochat.di

import com.github.dach83.oicochat.data.QuotesRepositoryImpl
import com.github.dach83.oicochat.data.remote.RemoteQuotesDataSource
import com.github.dach83.oicochat.data.remote.retrofit.RetrofitQuotesDataSource
import com.github.dach83.oicochat.domain.QuotesRepository
import com.github.dach83.oicochat.presentation.chat.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    includes(retrofitModule)

    single<RemoteQuotesDataSource> { RetrofitQuotesDataSource(get()) }

    single<QuotesRepository> { QuotesRepositoryImpl(get()) }

    viewModelOf(::ChatViewModel)
}
