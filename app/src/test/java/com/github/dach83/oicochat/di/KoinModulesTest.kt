package com.github.dach83.oicochat.di

import android.content.Context
import io.mockk.mockkClass
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule

class KoinModulesTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        mockkClass(clazz)
    }

    @Test
    fun `check koin modules in MainApplication`() {
        koinApplication {
            modules(appModule)
            checkModules {
                withInstance<Context>()
            }
        }
    }
}
