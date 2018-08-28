package com.test.endroits.home.presenter

import com.test.endroits.home.data.model.Venue
import com.test.endroits.home.domain.GetPlaces
import com.test.endroits.infrastructure.base.BasePresenter
import com.test.endroits.infrastructure.base.BaseView
import com.test.endroits.infrastructure.either
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomePresenter @Inject
constructor(private val getPlaces: GetPlaces): BasePresenter<HomePresenter.View>(){

    fun initialize(view: View){
        initializeView(view)
        getVenues()
    }

    private fun getVenues(){
        if(compositeDisposable.size() == 0) {
            compositeDisposable.add(
                    getPlaces.getVenues()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnComplete {
                                cleanUp()
                            }
                            .subscribe {
                                it.either(
                                        { view?.showEmpty() } ,
                                        { view?.showPlaces(it.response.venues.sortedWith(compareBy {it.location.distance.toInt()})) }
                                )
                            }
            )
        }
    }

    interface View: BaseView {
        fun showPlaces(places: List<Venue>)
        fun showEmpty()
    }
}