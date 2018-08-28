package com.test.endroits.home.ui

import android.support.v4.app.Fragment
import com.test.endroits.infrastructure.base.SingleFragmentActivity

class HomeActivity: SingleFragmentActivity(){

    override val fragment: Fragment
        get() = HomeFragment.newInstance()
}