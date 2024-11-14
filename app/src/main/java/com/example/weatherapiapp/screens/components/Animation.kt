package com.example.weatherapiapp.screens.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import com.example.weatherapiapp.R

@Composable
fun WeatherBackground(weatherCondition: String) {
    // Based on the weather condition, show the appropriate animation
    when (weatherCondition) {
        "Clear" -> ClearSkyAnimation()
        "Rain" -> RainAnimation()
        "Clouds" -> CloudySkyAnimation()
        else -> DefaultBackground()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClearSkyAnimation() {
    // Moving sun animation using infinite transition
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val sunOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 200f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.sunrise),
            contentDescription = null,
            modifier = Modifier
                .offset(x = sunOffset.dp)
                .size(100.dp)
        )
    }
}

@Composable
fun RainAnimation() {
    // Simulate raindrops using vertical animation
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val rainDropOffset by infiniteTransition.animateFloat(
        initialValue = -200f,
        targetValue = 600f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        // Simulate raindrops
        repeat(10) {
            Icon(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = null,
                modifier = Modifier
                    .offset(x = (it * 40).dp, y = rainDropOffset.dp)
                    .size(30.dp),
                tint = Color.Blue
            )
        }
    }
}

@Composable
fun CloudySkyAnimation() {
    // Clouds moving across the screen
    val infiniteTransition = rememberInfiniteTransition()
    val cloudOffset by infiniteTransition.animateFloat(
        initialValue = -200f,
        targetValue = 600f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
//        Icon(
//            painter = painterResource(id = R.drawable.ic_cloud),
//            contentDescription = null,
//            modifier = Modifier
//                .offset(x = cloudOffset.dp, y = 50.dp)
//                .size(100.dp)
//        )
    }
}

@Composable
fun DefaultBackground() {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)) {
        Text(text = "No Weather Data Available", modifier = Modifier.align(Alignment.Center))
    }
}
