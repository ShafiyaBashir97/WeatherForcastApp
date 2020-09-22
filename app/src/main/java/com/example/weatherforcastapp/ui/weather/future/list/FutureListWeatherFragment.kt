package com.example.weatherforcastapp.ui.weather.future.list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherforcastapp.R

class fututreListWeatherFragment : Fragment() {

    companion object {
        fun newInstance() = fututreListWeatherFragment()
    }

    private lateinit var viewModel: FututreListWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fututre_list_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FututreListWeatherViewModel::class.java)
        // TODO: Use the ViewModel
    }

}