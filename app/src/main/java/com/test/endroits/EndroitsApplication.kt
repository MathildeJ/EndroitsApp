package com.test.endroits

import android.app.Application
import com.test.endroits.infrastructure.di.components.AppComponent
import com.test.endroits.infrastructure.di.components.DaggerAppComponent
import com.test.endroits.infrastructure.di.modules.DataModule
import com.test.endroits.infrastructure.di.modules.LogicModule
import com.test.endroits.infrastructure.di.modules.MainModule
import com.test.endroits.infrastructure.di.modules.PresenterModule

class EndroitsApplication: Application(){

    private lateinit var component: AppComponent
    override fun onCreate() {
        super.onCreate()
        setupComponent()
    }
    private fun setupComponent() {
        component = DaggerAppComponent.builder()
                .mainModule(MainModule(this))
                .logicModule(LogicModule())
                .dataModule(DataModule())
                .presenterModule(PresenterModule())
                .build()
        component.inject(this)
    }
    fun getAppComponent(): AppComponent {
        return component
    }
    fun setComponent(appComponent: AppComponent) {
        this.component = appComponent
        this.component.inject(this)
    }
}