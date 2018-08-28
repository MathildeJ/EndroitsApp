package com.test.endroits.home.domain

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.test.endroits.FakeCall
import com.test.endroits.RxImmediateSchedulerRule
import com.test.endroits.details.data.Category
import com.test.endroits.home.data.PlacesService
import com.test.endroits.home.data.PlacesServiceFactory
import com.test.endroits.home.data.model.Location
import com.test.endroits.home.data.model.SearchVenuesResponse
import com.test.endroits.home.data.model.Venue
import com.test.endroits.home.data.model.VenuesResponse
import com.test.endroits.home.domain.model.NetworkError
import com.test.endroits.infrastructure.Either
import org.junit.Rule
import org.junit.Test
import retrofit2.mock.Calls

class GetPlacesInteractorTest{

    companion object {
        private val ANY_LOCATION = Location("", 0f)
        private val ANY_CATEGORY = Category("")
        private val ANY_CATEGORIES = List(0) { ANY_CATEGORY}
        private val ANY_PLACES = List(1) { Venue("", "", ANY_LOCATION, ANY_CATEGORIES, "", 0f) }
        private val ANY_VENUES_RESPONSE = VenuesResponse(ANY_PLACES)
        private val ANY_SEARCH_VENUES_RESPONSE = SearchVenuesResponse(ANY_VENUES_RESPONSE)
    }

    @Rule @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    private val placesServiceFactory: PlacesServiceFactory = mock()
    private val placesService: PlacesService = mock()
    private lateinit var getPlaces: GetPlaces


    @Test
    fun shouldGetPlacesIfCallSuccessful(){
        whenever(placesService.getVenues(any(), any(), any(), any())).thenReturn(Calls.response(ANY_SEARCH_VENUES_RESPONSE))
        whenever(placesServiceFactory.makeService()).thenReturn(placesService)
        getPlaces = GetPlacesInteractor(placesServiceFactory)

        val observable = getPlaces.getVenues().test()

        observable.assertValue(Either.Right(ANY_SEARCH_VENUES_RESPONSE))
    }

    @Test
    fun shouldReturnErrorIfCallUnsuccessful(){
        whenever(placesService.getVenues(any(), any(), any(), any())).thenReturn(FakeCall.buildHttpError(403, "", ""))
        whenever(placesServiceFactory.makeService()).thenReturn(placesService)
        getPlaces = GetPlacesInteractor(placesServiceFactory)

        val observable = getPlaces.getVenues().test()

        observable.assertValue(Either.Left(NetworkError.ERROR))
    }
}