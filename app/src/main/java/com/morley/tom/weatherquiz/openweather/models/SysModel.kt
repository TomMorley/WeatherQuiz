package com.morley.tom.weatherquiz.openweather.models

data class SysModel(
    val type:Int,
    val id: Int,
    val message : Float,
    val country: String,
    val sunrise : Int,
    val sunset: Int
)