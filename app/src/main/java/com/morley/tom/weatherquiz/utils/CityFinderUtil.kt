package com.morley.tom.weatherquiz.utils

import kotlin.random.Random

class CityFinderUtil {

    companion object {
        private val citiesArray = arrayListOf<String>(
            "Nottingham",
            "London",
            "Madrid",
            "Derby",
            "Barcelona",
            "Berlin",
            "York",
            "HongKong",
            "Paris",
            "Dublin",
            "Tokyo",
            "Stockholm",
            "Bangkok",
            "Hanoi"
        )

        fun getRandomCity() : String {
            return citiesArray.random()
        }

    }

}