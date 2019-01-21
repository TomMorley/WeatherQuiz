package com.morley.tom.weatherquiz.openweather.models

data class MainWeatherModel(
    val temp: Float,
    val pressure : Int,
    val humidity : Int,
    val temp_min : Float,
    val temp_max : Float
)