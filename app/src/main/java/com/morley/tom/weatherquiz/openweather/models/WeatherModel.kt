package com.morley.tom.weatherquiz.openweather.models

data class WeatherModel(
    val id : Int,
    val main : String,
    val description : String,
    val icon : String
)