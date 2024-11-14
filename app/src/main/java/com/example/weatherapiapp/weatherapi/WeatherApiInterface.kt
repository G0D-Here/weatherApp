package com.example.weatherapiapp.weatherapi

import com.example.weatherapiapp.data.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiInterface {
    @GET("/data/2.5/weather")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String = "761b423f7bb938aedf4a61afe193335d"
    ): WeatherResponse
}
