package com.test.endroits.home.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.test.endroits.R
import com.test.endroits.home.data.model.Venue
import com.test.endroits.home.presenter.HomePresenter
import com.test.endroits.infrastructure.base.BaseFragment
import com.test.endroits.infrastructure.di.components.FragmentComponent
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment: BaseFragment(), HomePresenter.View, OnVenueSelectionListener {

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment().apply {
                arguments = Bundle()
            }
        }
    }

    override val fragmentLayout: Int
        get() = R.layout.fragment_home

    override fun inject() {
        getComponent(FragmentComponent::class.java).inject(this)
    }


    @Inject lateinit var presenter: HomePresenter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.initialize(this)
    }

    override fun showPlaces(places: List<Venue>) {
        empty_view.visibility = View.GONE

        val adapter = VenueListAdapter(places, this)
        places_recycler.adapter = adapter
        places_recycler.layoutManager = LinearLayoutManager(activity)
        places_recycler.setHasFixedSize(true)
    }

    override fun showEmpty() {
        empty_view.visibility = View.VISIBLE
        empty_view.text = getString(R.string.empty_text)
    }

    override fun onVenueSelected(id: String) {

    }
}