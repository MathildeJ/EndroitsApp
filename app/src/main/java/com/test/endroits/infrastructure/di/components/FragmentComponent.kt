package com.test.endroits.infrastructure.di.components

import com.test.endroits.home.ui.HomeFragment
import com.test.endroits.infrastructure.di.annotations.PerActivity
import com.test.endroits.infrastructure.di.modules.ActivityModule
import dagger.Component

@PerActivity
@Component(dependencies = [(AppComponent::class)], modules = [(ActivityModule::class)])
interface FragmentComponent : ActivityComponent{
    fun inject(fragment: HomeFragment)
}
