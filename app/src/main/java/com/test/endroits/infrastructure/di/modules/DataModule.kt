package com.test.endroits.infrastructure.di.modules

import com.test.endroits.home.data.PlacesServiceFactory
import dagger.Module
import dagger.Provides

@Module
class DataModule{

    @Provides
    fun providePlacesServiceFactory(): PlacesServiceFactory{
        return PlacesServiceFactory()
    }
}