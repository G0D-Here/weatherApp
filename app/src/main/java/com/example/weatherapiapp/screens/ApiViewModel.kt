package com.example.weatherapiapp.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapiapp.data.WeatherResponse
import com.example.weatherapiapp.data.collect_in.Resource
import com.example.weatherapiapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _weatherResponse: MutableState<Resource<WeatherResponse>> =
        mutableStateOf(Resource.Loading)
    val weatherResponse: MutableState<Resource<WeatherResponse>> = _weatherResponse

    fun getWeather(lat: Double = 23.1735296, lon: Double = 79.9277056) {
        viewModelScope.launch {
            val result = repository.getWeatherData(lat, lon)
            _weatherResponse.value = result
        }
    }
}