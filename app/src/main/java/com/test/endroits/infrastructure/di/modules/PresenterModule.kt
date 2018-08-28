package com.test.endroits.infrastructure.di.modules

import com.test.endroits.home.domain.GetPlaces
import com.test.endroits.home.presenter.HomePresenter
import dagger.Module
import dagger.Provides

@Module
class PresenterModule{

    @Provides
    fun provideHomePresenter(getPlaces: GetPlaces): HomePresenter {
        return HomePresenter(getPlaces)
    }
}