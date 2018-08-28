package com.test.endroits.home.data.model

import com.google.gson.annotations.SerializedName

data class Venue(@SerializedName("id") val id: String,
                 @SerializedName("name") val name: String,
                 @SerializedName("location") val location: Location
)