package com.test.endroits.infrastructure.di.components

import com.test.endroits.EndroitsApplication
import com.test.endroits.home.presenter.HomePresenter
import com.test.endroits.infrastructure.base.BaseActivity

interface MainComponent{
    fun inject(`object`: EndroitsApplication)
    fun inject(activity: BaseActivity)

    fun getHomePresenter(): HomePresenter
}