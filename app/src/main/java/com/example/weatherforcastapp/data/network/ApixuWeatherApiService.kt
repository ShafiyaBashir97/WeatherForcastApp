package com.example.weatherforcastapp.data.network

import com.example.weatherforcastapp.data.db.entity.CurrentWeatherResponse


import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


//http://api.weatherstack.com/current?access_key=2134bf11701bfb35cdfaaf826d709cab&query=New%20York&lang=en

const val API_KEY="2134bf11701bfb35cdfaaf826d709cab"
interface ApixuWeatherApiService
{
    // this interface will be used by retrofit to fetch data from API

    @GET("current")
   suspend fun getCurrentWeather(
        @Query("query") location: String,
    ): CurrentWeatherResponse

    

    //Its better to pass query parameter with requestinyerceptor as query parameter
    //the apikey can be put into every single call of APIXU weather api service
    //to actually intercept the api call requestinterceptor is passed to okhttp

    companion object {
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor

        ): ApixuWeatherApiService

        {
            val requestInterceptor = Interceptor { chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)               // setting up the client
                .baseUrl("https://api.weatherstack.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApixuWeatherApiService::class.java) }
    }
}



