package com.example.weatherapiapp.repository

import com.example.weatherapiapp.data.WeatherResponse
import com.example.weatherapiapp.data.collect_in.Resource
import com.example.weatherapiapp.weatherapi.WeatherApiInterface
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: WeatherApiInterface) {
    suspend fun getWeatherData(lat: Double, lon: Double): Resource<WeatherResponse> {
        return try {
            val result = apiService.getWeather(lat, lon)
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Failure(Exception(e.message.toString()))
        }
    }
}