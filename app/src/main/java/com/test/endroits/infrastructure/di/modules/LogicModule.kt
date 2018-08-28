package com.test.endroits.infrastructure.di.modules

import com.test.endroits.home.domain.GetPlaces
import com.test.endroits.home.domain.GetPlacesInteractor
import dagger.Module
import dagger.Provides

@Module
class LogicModule{

    @Provides
    fun provideGetPlaces(interactor: GetPlacesInteractor): GetPlaces {
        return interactor
    }
}