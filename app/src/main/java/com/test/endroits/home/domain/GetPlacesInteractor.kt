package com.test.endroits.home.domain

import com.test.endroits.home.data.PlacesService
import com.test.endroits.home.data.PlacesServiceFactory
import com.test.endroits.home.data.model.SearchVenuesResponse
import com.test.endroits.home.domain.model.NetworkError
import com.test.endroits.infrastructure.Either
import io.reactivex.Observable
import javax.inject.Inject


class GetPlacesInteractor @Inject
constructor(private val placesServiceFactory: PlacesServiceFactory) : GetPlaces{

    companion object {
        private const val coordinates = "45.5332969,-73.6551793" //Koolicar office
        private const val limit = 10
        private const val radius = 2500
        private const val categoryId = "4d4b7105d754a06374d81259" //Food
    }

    private val placesService: PlacesService = placesServiceFactory.makeService()

    override fun getVenues(): Observable<Either<NetworkError, SearchVenuesResponse>> {
        return Observable.create{ subscriber ->
            val call = placesService.getVenues(coordinates, limit, categoryId, radius).execute()
            if(call.isSuccessful){
                val response = call.body()
                if(response != null) {
                    subscriber.onNext(Either.Right(response))
                } else {
                    subscriber.onComplete()
                }
            } else {
                subscriber.onNext(Either.Left(NetworkError.ERROR))
            }
        }
    }
}