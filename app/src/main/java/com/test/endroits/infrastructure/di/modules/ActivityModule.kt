package com.test.endroits.infrastructure.di.modules

import android.content.Context
import com.test.endroits.infrastructure.base.BaseActivity
import com.test.endroits.infrastructure.di.annotations.PerActivity
import dagger.Module
import dagger.Provides


@Module
class ActivityModule(private val activity: BaseActivity) {

    @Provides @PerActivity
    internal fun provideActivityContext(): Context {
        return activity
    }
}