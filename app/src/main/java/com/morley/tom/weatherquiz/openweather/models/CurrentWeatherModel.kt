package com.morley.tom.weatherquiz.openweather.models

data class CurrentWeatherModel(
    val coord : CoordModel,
    val weather : List<WeatherModel>,
    val base : String,
    val main : MainWeatherModel,
    val visibility : Int,
    val wind : WindModel,
    val clouds : CloudsModel,
    val dt : Int,
    val sys : SysModel,
    val id : Int,
    val name : String,
    val cod : Int
)