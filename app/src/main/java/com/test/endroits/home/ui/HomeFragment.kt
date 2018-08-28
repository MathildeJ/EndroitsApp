package com.test.endroits.home.ui

import android.os.Bundle
import com.test.endroits.R
import com.test.endroits.infrastructure.base.BaseFragment
import com.test.endroits.infrastructure.di.components.FragmentComponent

class HomeFragment: BaseFragment() {

    override val fragmentLayout: Int
        get() = R.layout.fragment_home

    override fun inject() {
        getComponent(FragmentComponent::class.java).inject(this)
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment().apply {
                arguments = Bundle()
            }
        }
    }
}