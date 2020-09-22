package com.example.weatherforcastapp.data.network

import androidx.lifecycle.LiveData
import com.example.weatherforcastapp.data.db.entity.CurrentWeatherResponse

interface WeatherNetworkDataSourc {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(
        location: String,
    )

}