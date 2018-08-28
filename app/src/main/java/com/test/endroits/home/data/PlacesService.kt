package com.test.endroits.home.data

import com.test.endroits.details.data.VenueDetailsResponse
import com.test.endroits.home.data.model.SearchVenuesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlacesService{

    @GET("venues/search")
    fun getVenues(@Query("ll") ll: String, @Query("limit") limit: Number,
                  @Query("categoryId") categoryId: String, @Query("radius") radius: Number)
            : Call<SearchVenuesResponse>

    @GET("venues/{id}")
    fun getVenueDetails(@Path("id") id: String): Call<VenueDetailsResponse>
}