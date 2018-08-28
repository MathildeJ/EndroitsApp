package com.test.endroits.home.data.model

import com.google.gson.annotations.SerializedName

data class Location(
        @SerializedName("address") val address: String,
        @SerializedName("distance") val distance: Number
)