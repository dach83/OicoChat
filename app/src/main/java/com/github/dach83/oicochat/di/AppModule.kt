package com.github.dach83.oicochat.di

import com.github.dach83.oicochat.data.QuotesRepositoryImpl
import com.github.dach83.oicochat.domain.QuotesRepository
import com.github.dach83.oicochat.presentation.chat.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    single<QuotesRepository> { QuotesRepositoryImpl() }

    viewModelOf(::ChatViewModel)
}
