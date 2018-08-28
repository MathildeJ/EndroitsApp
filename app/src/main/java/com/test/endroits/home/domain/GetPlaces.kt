package com.test.endroits.home.domain

import com.test.endroits.home.data.model.SearchVenuesResponse
import com.test.endroits.home.domain.model.NetworkError
import com.test.endroits.infrastructure.Either
import io.reactivex.Observable

interface GetPlaces{
    fun execute(): Observable<Either<NetworkError,SearchVenuesResponse>>

    operator fun invoke() = this.execute()
}