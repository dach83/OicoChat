package com.github.dach83.oicochat.di

import org.koin.dsl.module

val appModule = module {

    includes(
        chatModule
    )
}
