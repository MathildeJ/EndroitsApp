package com.test.endroits.details.ui

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import com.test.endroits.infrastructure.base.SingleFragmentActivity

class DetailsActivity: SingleFragmentActivity(){

    companion object {

        private const val EXTRA_VENUE_ID = "DetailsActivity.EXTRA_VENUE_ID"

        fun getLaunchIntent(context: Context, venueId: String): Intent {
            return Intent(context, DetailsActivity::class.java).apply {
                putExtra(EXTRA_VENUE_ID, venueId)
            }
        }
    }

    override val fragment: Fragment
        get() = DetailsFragment.newInstance(intent.getStringExtra(EXTRA_VENUE_ID))
}