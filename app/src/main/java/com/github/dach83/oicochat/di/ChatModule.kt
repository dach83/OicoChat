package com.github.dach83.oicochat.di

import com.github.dach83.oicochat.presentation.chat.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val chatModule = module {

    viewModelOf(::ChatViewModel)
}
