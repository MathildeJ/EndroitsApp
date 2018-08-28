package com.test.endroits.details.presenter

import com.test.endroits.details.domain.GetVenueDetails
import com.test.endroits.infrastructure.base.BasePresenter
import com.test.endroits.infrastructure.base.BaseView
import com.test.endroits.infrastructure.either
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailsPresenter @Inject
constructor(private val getVenueDetails: GetVenueDetails): BasePresenter<DetailsPresenter.View>(){

    fun initialize(view: View, venueId: String){
        initializeView(view)
        subscribeToGetVenueDetails(venueId)
    }

    private fun subscribeToGetVenueDetails(venueId: String){
        if(compositeDisposable.size() == 0) {
            compositeDisposable.add(
                    getVenueDetails(venueId)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnComplete {
                                cleanUp()
                            }
                            .subscribe {
                                it.either(
                                        { view?.showError() } ,
                                        { view?.showVenueDetails(
                                                it.response.venue.name, it.response.venue.location.address,
                                                it.response.venue.categories.getOrNull(0)?.name,
                                                it.response.venue.url, it.response.venue.rating)
                                        }
                                )
                            }
            )
        }
    }

    interface View: BaseView{
        fun showVenueDetails(venueName: String, venueAddress: String, venueCategory: String?, venueUrl: String?, venueRating: Float)
        fun showError()
    }
}