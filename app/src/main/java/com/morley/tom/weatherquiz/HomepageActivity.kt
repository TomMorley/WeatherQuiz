package com.morley.tom.weatherquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.morley.tom.weatherquiz.base.BaseActivity
import com.morley.tom.weatherquiz.models.AnswerModel
import com.morley.tom.weatherquiz.models.QuestionModel
import com.morley.tom.weatherquiz.openweather.OpenWeatherService
import com.morley.tom.weatherquiz.openweather.models.CurrentWeatherModel
import com.morley.tom.weatherquiz.utils.CityFinderUtil
import com.morley.tom.weatherquiz.utils.WeatherUnits
import kotlinx.android.synthetic.main.activity_homepage.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomepageActivity : BaseActivity() {


    override fun getLayoutId(): Int {
        return R.layout.activity_homepage
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initViews() {
        startBtn.setOnClickListener {
            startActivity(Intent(applicationContext, QuizActivity::class.java))
        }

    }


}
