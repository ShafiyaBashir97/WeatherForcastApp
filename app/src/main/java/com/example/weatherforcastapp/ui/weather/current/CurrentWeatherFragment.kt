package com.example.weatherforcastapp.ui.weather.current

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherforcastapp.R
import com.example.weatherforcastapp.data.network.ApixuWeatherApiService
import com.example.weatherforcastapp.data.network.ConnectivityInterceptorImpl
import com.example.weatherforcastapp.data.network.WeatherNetworkDataSourc
import com.example.weatherforcastapp.data.network.WeatherNetworkDataSourcImpl
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class currentWeatherFragment : Fragment() {

    companion object {
        fun newInstance() = currentWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       viewModel= ViewModelProvider(this).get(CurrentWeatherViewModel::class.java)

        val apiService= ApixuWeatherApiService(ConnectivityInterceptorImpl(this.requireContext()))
        val weatherNetworkDataSourc=WeatherNetworkDataSourcImpl(apiService)
        weatherNetworkDataSourc.downloadedCurrentWeather.observe(this , Observer {
            textview.text=it.toString()
        })
        GlobalScope.launch(Dispatchers.Main)
        {
           // val currentweatherresponse= apiService.getCurrentWeather("New York")
            weatherNetworkDataSourc.fetchCurrentWeather("London")

        }

      }


}