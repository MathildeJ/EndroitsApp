package com.test.endroits.home.domain

import com.test.endroits.details.data.VenueDetailsResponse
import com.test.endroits.home.data.PlacesService
import com.test.endroits.home.data.model.SearchVenuesResponse
import com.test.endroits.home.domain.model.NetworkError
import com.test.endroits.infrastructure.Either
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class GetPlacesInteractor @Inject
constructor() : GetPlaces{

    companion object {
        private const val BASE_URL = "https://api.foursquare.com/v2/"
        internal const val clientId = "FDTLVFLIEAAFELTURBUK045RVB0ZSQRYPZCOFGYYOQERI3VM"
        internal const val clientSecret = "BVDJXSME2LEVM3ZF1JPN1PUYP42KYPTKYTDVDCW5LUYS02P0"
        internal const val version = "20180827"

        private const val coordinates = "45.5332969,-73.6551793" //Koolicar office
        private const val limit = 10
        private const val radius = 2500
        private const val categoryId = "4d4b7105d754a06374d81259" //Food
    }

    private val httpClient = OkHttpClient.Builder()

    private val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

    private var retrofit = builder.client(httpClient.build()).build()
    private val placesService: PlacesService

    init{
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("client_id", clientId)
                    .addQueryParameter("client_secret", clientSecret)
                    .addQueryParameter("v", version)
                    .build()

            val request = original.newBuilder().url(url).build()
            chain.proceed(request)
        }

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(httpLoggingInterceptor)

        builder.client(httpClient.build())
        retrofit = builder.build()
        placesService = retrofit.create(PlacesService::class.java)
    }


    override fun getVenues(): Observable<Either<NetworkError, SearchVenuesResponse>> {
        return Observable.create{ subscriber ->
            val call = placesService
                    .getVenues(coordinates, limit, categoryId, radius).execute()
            if(call.isSuccessful){
                val response = call.body()
                if(response != null) {
                    subscriber.onNext(Either.Right(response))
                } else {
                    subscriber.onComplete()
                }
            } else {
                subscriber.onNext(Either.Left(NetworkError.ERROR))
            }
        }
    }

    override fun getVenueDetails(venueId: String): Observable<Either<NetworkError, VenueDetailsResponse>> {
        return Observable.create{ subscriber ->
            val call = placesService.getVenueDetails(venueId).execute()
            if(call.isSuccessful){
                val response = call.body()
                if(response != null) {
                    subscriber.onNext(Either.Right(response))
                } else {
                    subscriber.onComplete()
                }
            } else {
                subscriber.onNext(Either.Left(NetworkError.ERROR))
            }
        }
    }
}