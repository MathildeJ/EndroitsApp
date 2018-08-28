package com.test.endroits.home.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 'open' modifier needed for tests
open class PlacesServiceFactory{

    companion object {
        private const val BASE_URL = "https://api.foursquare.com/v2/"
        private const val clientId = "FDTLVFLIEAAFELTURBUK045RVB0ZSQRYPZCOFGYYOQERI3VM"
        private const val clientSecret = "BVDJXSME2LEVM3ZF1JPN1PUYP42KYPTKYTDVDCW5LUYS02P0"
        private const val version = "20180827"
    }

    open fun makeService(): PlacesService {
        val httpClient = OkHttpClient.Builder()
        val builder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

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
        val retrofit = builder.build()
        return retrofit.create(PlacesService::class.java)
    }
}