package com.example.weatherforcastapp.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherforcastapp.data.db.entity.CurrentWeatherResponse
import com.example.weatherforcastapp.internal.NoConnectivityException

class WeatherNetworkDataSourcImpl(
    private val apixuWeatherApiService: ApixuWeatherApiService
) : WeatherNetworkDataSourc {

    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String) {
        try {
            val fetchedCurrentWeather = apixuWeatherApiService
                .getCurrentWeather(location)
            _downloadedCurrentWeather.postValue(fetchedCurrentWeather)
        }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }

    }
}