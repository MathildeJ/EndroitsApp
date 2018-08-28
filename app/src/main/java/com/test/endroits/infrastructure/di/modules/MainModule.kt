package com.test.endroits.infrastructure.di.modules

import android.content.Context
import com.test.endroits.EndroitsApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule(private val app: EndroitsApplication) {

    @Provides @Singleton
    fun provideApplicationContext(): Context {
        return app
    }
}