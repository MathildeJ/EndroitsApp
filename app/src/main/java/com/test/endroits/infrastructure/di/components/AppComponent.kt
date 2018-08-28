package com.test.endroits.infrastructure.di.components

import com.test.endroits.infrastructure.di.modules.LogicModule
import com.test.endroits.infrastructure.di.modules.MainModule
import com.test.endroits.infrastructure.di.modules.PresenterModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(MainModule::class), (LogicModule::class), (PresenterModule::class)])
interface AppComponent : MainComponent {
}