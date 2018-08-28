package com.test.endroits.home.data.model

import com.google.gson.annotations.SerializedName

data class VenuesResponse(@SerializedName("venues") val venues: List<Venue>)