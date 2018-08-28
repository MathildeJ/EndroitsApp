package com.test.endroits.details.ui

import android.os.Bundle
import android.view.View
import com.test.endroits.R
import com.test.endroits.details.presenter.DetailsPresenter
import com.test.endroits.infrastructure.base.BaseFragment
import com.test.endroits.infrastructure.di.components.FragmentComponent
import kotlinx.android.synthetic.main.fragment_venue_details.*
import javax.inject.Inject

class DetailsFragment: BaseFragment(), DetailsPresenter.View{

    companion object {
        private const val EXTRA_VENUE_ID = "DetailsFragment.EXTRA_VENUE_ID"

        fun newInstance(venueId: String): DetailsFragment {
            return DetailsFragment().apply {
                arguments = Bundle()
                arguments.putString(EXTRA_VENUE_ID, venueId)
            }
        }
    }

    override val fragmentLayout: Int
        get() = R.layout.fragment_venue_details

    override fun inject() {
        getComponent(FragmentComponent::class.java).inject(this)
    }

    @Inject lateinit var presenter: DetailsPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.initialize(this, arguments.getString(EXTRA_VENUE_ID))
        setButtonOnClickListener()
    }

    private fun setButtonOnClickListener(){
        validate_button.setOnClickListener { activity.finish() }
    }

    override fun showVenueDetails(venueName: String, venueAddress: String, venueCategory: String?,
                                  venueUrl: String?, venueRating: Float) {
        venue_name.text = getString(R.string.venue_name, venueName)
        venue_address.text = getString(R.string.venue_address, venueAddress)
        if(venueCategory != null) {
            venue_category.text = getString(R.string.venue_category, venueCategory)
        } else {
            venue_category.visibility = View.GONE
        }
        if(venueUrl != null){
            venue_url.text = getString(R.string.venue_url, venueUrl)
        } else {
            venue_url.visibility = View.GONE
        }
        venue_rating.text = getString(R.string.venue_rating, venueRating)
        validate_button.visibility = View.VISIBLE
    }

    override fun showError() {
        venue_name.text = getString(R.string.venue_details_error)
        validate_button.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.cleanUp()
    }
}