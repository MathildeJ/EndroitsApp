package com.test.endroits.home.data.model

import com.google.gson.annotations.SerializedName
import com.test.endroits.details.data.Category

data class Venue(@SerializedName("id") val id: String,
                 @SerializedName("name") val name: String,
                 @SerializedName("location") val location: Location,
                 @SerializedName("categories") val categories: List<Category>,
                 @SerializedName("url") val url: String?,
                 @SerializedName("rating") val rating: Float
)