package com.test.endroits.home.domain

import com.test.endroits.details.data.VenueDetailsResponse
import com.test.endroits.home.data.model.SearchVenuesResponse
import com.test.endroits.home.domain.model.NetworkError
import com.test.endroits.infrastructure.Either
import io.reactivex.Observable

interface GetPlaces{
    fun getVenues(): Observable<Either<NetworkError, SearchVenuesResponse>>
    fun getVenueDetails(venueId: String): Observable<Either<NetworkError, VenueDetailsResponse>>
}