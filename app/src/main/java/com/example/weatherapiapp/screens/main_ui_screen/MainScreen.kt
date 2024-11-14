package com.example.weatherapiapp.screens.main_ui_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapiapp.R
import com.example.weatherapiapp.data.collect_in.Resource
import com.example.weatherapiapp.screens.ApiViewModel
import com.example.weatherapiapp.screens.components.SearchBar
import com.example.weatherapiapp.screens.components.TempCard
import com.example.weatherapiapp.screens.components.TemperatureCard
import com.example.weatherapiapp.screens.components.WindCard

@Composable
fun MainScreen(viewModel: ApiViewModel = hiltViewModel()) {
    val weatherData by viewModel.weatherResponse
    LaunchedEffect(Unit) {
        viewModel.getWeather()
    }
    weatherData.let {
        when (it) {
            is Resource.Failure -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
                    Text(text = it.exception.message.toString())
                }
            }

            Resource.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
                    CircularProgressIndicator()
                }
            }

            is Resource.Success -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.weatherui),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize() // Ensure the image covers the whole box
                    )
                    Column(
                        modifier = Modifier
                            .padding(top = 40.dp)
                            .fillMaxSize()
                            .verticalScroll(state = rememberScrollState()),

                        ) {
                        Spacer(modifier = Modifier.height(10.dp))
                        SearchBar(weather = it.result)
                        TemperatureCard(it.result.main, it.result.weather.first(), it.result)
                        Row(
                            Modifier
                                .padding(start = 20.dp, end = 20.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Card(
                                Modifier
                                    .padding(4.dp)
                                    .weight(.1f),
                                colors = CardDefaults.cardColors(Color(0x3B000000))

                            ) {
                                Column(Modifier.padding(5.dp)) {
                                    Text(
                                        text = "SunRise",
                                        Modifier.fillMaxWidth(),
                                        color = Color.White,
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        text = formatUnixTime(it.result.sys.sunrise),
                                        Modifier.fillMaxWidth(),
                                        color = Color.White,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                            Card(
                                Modifier
                                    .padding(4.dp)
                                    .weight(.1f),
                                colors = CardDefaults.cardColors(Color(0x3B000000))
                            ) {
                                Column(Modifier.padding(5.dp)) {
                                    Text(
                                        text = "SunSet",
                                        Modifier.fillMaxWidth(),
                                        color = Color.White,
                                        textAlign = TextAlign.Center
                                    )
                                    Text(
                                        text = formatUnixTime(it.result.sys.sunset),
                                        Modifier.fillMaxWidth(),
                                        color = Color.White,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            WindCard(wind = it.result.wind)
                            TempCard(main = it.result.main)
                        }

                    }
                }
            }
        }

    }
}

fun formatUnixTime(unixTime: Int): String {
    // Convert the Unix timestamp (in seconds) to milliseconds
    val date = java.util.Date(unixTime.toLong() * 1000)
    // Format the date to a readable time format
    val format = java.text.SimpleDateFormat("hh:mm a", java.util.Locale.getDefault())
    return format.format(date)
}
