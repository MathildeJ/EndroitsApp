package com.test.endroits.details.domain

import com.test.endroits.details.data.VenueDetailsResponse
import com.test.endroits.home.domain.model.NetworkError
import com.test.endroits.infrastructure.Either
import io.reactivex.Observable

interface GetVenueDetails{
    fun getVenueDetails(venueId: String): Observable<Either<NetworkError, VenueDetailsResponse>>

    operator fun invoke(venueId: String) = this.getVenueDetails(venueId)
}