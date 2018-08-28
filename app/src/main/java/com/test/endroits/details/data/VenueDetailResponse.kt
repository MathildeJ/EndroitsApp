package com.test.endroits.details.data

import com.google.gson.annotations.SerializedName
import com.test.endroits.home.data.model.Venue

data class VenueDetailResponse(@SerializedName("venue") val venue: Venue)