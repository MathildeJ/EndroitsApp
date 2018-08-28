package com.test.endroits.details.domain

import com.test.endroits.details.data.VenueDetailsResponse
import com.test.endroits.home.data.PlacesServiceFactory
import com.test.endroits.home.domain.model.NetworkError
import com.test.endroits.infrastructure.Either
import io.reactivex.Observable
import javax.inject.Inject

class GetVenueDetailsInteractor @Inject
constructor(placesServiceFactory: PlacesServiceFactory): GetVenueDetails{

    private val placesService = placesServiceFactory.makeService()

    override fun getVenueDetails(venueId: String): Observable<Either<NetworkError, VenueDetailsResponse>> {
        return Observable.create{ subscriber ->
            val call = placesService.getVenueDetails(venueId).execute()
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