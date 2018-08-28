package com.test.endroits.infrastructure.di.components


import android.content.Context
import com.test.endroits.infrastructure.di.PerActivity
import com.test.endroits.infrastructure.di.modules.ActivityModule

import dagger.Component

@PerActivity
@Component(dependencies = [(AppComponent::class)], modules = [(ActivityModule::class)])
interface ActivityComponent {
    fun activity(): Context
}