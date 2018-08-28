package com.test.endroits.home.ui

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.test.endroits.infrastructure.base.SingleFragmentActivity

class HomeActivity: SingleFragmentActivity(){

    companion object {
        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    override val fragment: Fragment
        get() = HomeFragment.newInstance()
}