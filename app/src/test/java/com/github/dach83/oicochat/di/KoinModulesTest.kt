package com.github.dach83.oicochat.di

import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.check.checkModules

class KoinModulesTest {

    @Test
    fun `check koin modules in MainApplication`() {
        koinApplication {
            modules(appModule)
            checkModules()
        }
    }
}
