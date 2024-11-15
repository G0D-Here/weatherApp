package com.example.weatherapiapp.screens.components

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.weatherapiapp.data.Main
import com.example.weatherapiapp.data.Weather
import com.example.weatherapiapp.data.WeatherResponse
import com.example.weatherapiapp.data.Wind
import com.example.weatherapiapp.screens.ApiViewModel
import com.example.weatherapiapp.screens.main_ui_screen.formatUnixTime
import com.google.android.gms.location.LocationServices
import kotlin.math.roundToInt

@Composable
fun TemperatureCard(main: Main, weather: Weather, weatherResponse: WeatherResponse) {
    val time by remember {
        mutableStateOf(formatUnixTime(weatherResponse.dt))
    }
    Card(
        Modifier
            .padding(25.dp),
        colors = CardDefaults.cardColors(Color.Transparent),
    ) {
        Column(
            Modifier
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = time,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color.White

                )
            }
            val temperatureInCelsius = main.temp - 273.15
            val formattedTemperature = "${temperatureInCelsius.roundToInt()}°"

            Text(
                text = formattedTemperature,
                Modifier.padding(start = 20.dp),
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 100.sp,
            )
            Text(
                text = weather.description,
                Modifier.padding(5.dp),
                color = Color.White,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Pressure ${main.pressure}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    color = Color.White

                )
                Text(
                    text = "Humidity ${main.humidity}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    color = Color.White

                )
            }
        }
    }
}

@Composable
fun WindCard(modifier: Modifier = Modifier, wind: Wind) {
    Card(
        modifier
            .padding(10.dp),
        colors = CardDefaults.cardColors(Color(0x3B000000))
    ) {
        Column(
            Modifier
                .padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Wind", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Column(
                    Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Speed", color = Color.White, fontSize = 10.sp)
                    Text(
                        text = wind.speed.toString(), color = Color.White,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Column(
                    Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Degree", color = Color.White, fontSize = 10.sp)
                    Text(
                        text = wind.deg.toString(),
                        color = Color.White,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Column(
                    Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Gust", color = Color.White, fontSize = 10.sp)
                    Text(
                        text = wind.gust.toString(),
                        color = Color.White,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}


@Composable
fun TempCard(modifier: Modifier = Modifier, main: Main) {
    Card(
        modifier
            .padding(top = 12.dp, bottom = 10.dp, end = 10.dp),
        colors = CardDefaults.cardColors(Color(0x3B000000))
    ) {
        Column(
            Modifier
                .padding(2.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Temp at",
                Modifier.padding(top = 6.dp),
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    lineHeight = 1.1.em
                )
            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(
                    Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Sea\nLevel", color = Color.White,
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            lineHeight = 1.em
                        )
                    )
                    Text(
                        text = "${main.sea_level - 273.15}∘C", color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Column(
                    Modifier.padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Ground\nLevel", color = Color.White,
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            lineHeight = 1.em
                        )
                    )
                    Text(
                        text = "${main.grnd_level - 273.15}∘C", color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

    }
}

@Composable
fun SearchBar(weather: WeatherResponse) {
    Card(
        Modifier
            .padding(start = 25.dp, end = 25.dp),
        colors = CardDefaults.cardColors(Color(0x3B000000))
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                Modifier.padding(start = 5.dp),
            )
            Text(
                text = "${weather.name} ,${weather.sys.country}",
                Modifier.padding(start = 70.dp),
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun CurrentLocationComponent(context: Context, viewModel: ApiViewModel) {
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    var locationText by remember { mutableStateOf("Fetching location...") }
    val lat = remember { mutableStateOf<Double?>(23.1735296) }
    val lon = remember { mutableStateOf<Double?>(79.9277056) }

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    location.latitude = lat.value!!
                    location.longitude = lon.value!!
                    locationText =
                        "Latitude: ${location.latitude}, Longitude: ${location.longitude}"
                } else {
                    locationText = "Location not available"
                }
            }.addOnFailureListener {
                locationText = "Failed to get location"
            }
        } else {
            locationText = "Permission not granted"
        }
    }
    viewModel.getWeather(lat = lat.value!!, lon = lon.value!!)
}











