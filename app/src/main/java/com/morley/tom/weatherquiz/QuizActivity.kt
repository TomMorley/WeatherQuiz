package com.morley.tom.weatherquiz

import android.annotation.SuppressLint
import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.morley.tom.weatherquiz.base.BaseActivity
import com.morley.tom.weatherquiz.models.AnswerModel
import com.morley.tom.weatherquiz.models.QuestionModel
import com.morley.tom.weatherquiz.openweather.OpenWeatherService
import com.morley.tom.weatherquiz.openweather.models.CurrentWeatherModel
import com.morley.tom.weatherquiz.utils.CityFinderUtil
import com.morley.tom.weatherquiz.utils.WeatherUnits
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.fragment_question.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuizActivity : BaseActivity(), QuestionFragment.QuizNavigator {


    private lateinit var apiService : OpenWeatherService
    private var questionsAnswered : Int = 0
    private val totalQuestions : Int = 5
    private var score : Int = 0


    override fun getLayoutId(): Int {
        return R.layout.activity_quiz
    }

    @SuppressLint("CheckResult")
    override fun initViews() {
        super.initViews()
    }

    override fun onAttachFragment(fragment: androidx.fragment.app.Fragment?) {
        super.onAttachFragment(fragment)
        fragment as QuestionFragment
        fragment.setNavigator(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiService = OpenWeatherService.getWeatherApi().create(OpenWeatherService::class.java)
        getCurrentWeatherData()
    }

    override fun correctAnswerClicked() {
        score++
        Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        questionAnswered()
    }

    override fun incorrectAnswerClicked() {
        Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
        questionAnswered()
    }

    private fun questionAnswered(){
        questionsAnswered++
        if(totalQuestions <= questionsAnswered){
            goToReviewScreen(score)
        } else getCurrentWeatherData()
    }

    private fun goToReviewScreen(score : Int){
        val intent = Intent(this, ReviewActivity::class.java)
        intent.putExtra("score", score)
        startActivity(intent)
    }

    private fun getCurrentWeatherData(){
        var city = CityFinderUtil.getRandomCity()
        val call = apiService.getWeather(city, OpenWeatherService.apiKey, WeatherUnits.METRIC.unitName)
        call.enqueue(object : Callback<CurrentWeatherModel> {
            override fun onFailure(call: Call<CurrentWeatherModel>, t: Throwable) {
                println(city)
                Log.d("Error", t.message)
            }
            override fun onResponse(call: Call<CurrentWeatherModel>, response: Response<CurrentWeatherModel>) {
                if(response.isSuccessful){
                    println(city)
                    val questionFragment = QuestionFragment()
                    questionFragment.setQuestion(createQuestionModels(response.body()!!))
                    loadFragment(questionFragment, questionFrame.id)
                } else getCurrentWeatherData()
            }
        })
    }

    private fun createQuestionModels(currentWeather : CurrentWeatherModel) : QuestionModel {
        val numOfAnswers = 3
        val answers = ArrayList<AnswerModel>()
        val correctAnswer = AnswerModel(
            currentWeather.name,
            true
        )
        answers.add(correctAnswer)
        for(x in 1..(numOfAnswers-1)){
            val answer = AnswerModel(
                CityFinderUtil.getRandomCity(),
                false
            )
            answers.add(answer)
        }
        return QuestionModel(
            currentWeather.weather.first().icon,
            currentWeather.weather.first().description,
            currentWeather.main.temp,
            currentWeather.name,
            answers
        )
    }
}



