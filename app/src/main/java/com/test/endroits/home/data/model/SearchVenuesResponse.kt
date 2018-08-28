package com.test.endroits.home.data.model

import com.google.gson.annotations.SerializedName

data class SearchVenuesResponse(@SerializedName("response") val response: VenuesResponse)