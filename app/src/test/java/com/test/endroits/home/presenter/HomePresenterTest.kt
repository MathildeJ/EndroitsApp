package com.test.endroits.home.presenter

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.test.endroits.RxImmediateSchedulerRule
import com.test.endroits.details.data.Category
import com.test.endroits.home.data.model.Location
import com.test.endroits.home.data.model.SearchVenuesResponse
import com.test.endroits.home.data.model.Venue
import com.test.endroits.home.data.model.VenuesResponse
import com.test.endroits.home.domain.GetPlaces
import com.test.endroits.home.domain.model.NetworkError
import com.test.endroits.infrastructure.Either
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomePresenterTest{

    companion object {
        private val ANY_LOCATION = Location("", 0f)
        private val ANY_CATEGORY = Category("")
        private val ANY_CATEGORIES = List(0) { ANY_CATEGORY}
        private val ANY_PLACES = List(1) {Venue("", "", ANY_LOCATION, ANY_CATEGORIES, "", 0f)}
        private val ANY_VENUES_RESPONSE = VenuesResponse(ANY_PLACES)
        private val ANY_SEARCH_VENUES_RESPONSE = SearchVenuesResponse(ANY_VENUES_RESPONSE)
    }

    @Rule @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    private val view: HomePresenter.View = mock()
    private val getPlaces: GetPlaces = mock()

    lateinit var presenter: HomePresenter

    @Before
    fun setup(){
        whenever(getPlaces()).thenReturn(Observable.never())
        presenter = HomePresenter(getPlaces)
    }

    @Test
    fun shouldGetPlacesOnInit(){
        presenter.initialize(view)

        verify(getPlaces).invoke()
    }

    @Test
    fun shouldUpdatePlacesOnPlacesFound(){
        whenever(getPlaces()).thenReturn(Observable.just(Either.Right(ANY_SEARCH_VENUES_RESPONSE)))
        presenter.initialize(view)

        verify(view).showPlaces(ANY_PLACES)
    }

    @Test
    fun shouldShowEmptyOnError(){
        whenever(getPlaces()).thenReturn(Observable.just(Either.Left(NetworkError.ERROR)))
        presenter.initialize(view)

        verify(view).showEmpty()
    }
}