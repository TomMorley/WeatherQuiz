package com.morley.tom.weatherquiz.models

import com.morley.tom.weatherquiz.models.AnswerModel

data class QuestionModel (
    var weatherIcon : String,
    var weatherDescription : String,
    var currentTemp : Float,
    var correctLocation : String,
    var answers : List<AnswerModel>
)