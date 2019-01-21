package com.morley.tom.weatherquiz.openweather

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.morley.tom.weatherquiz.openweather.models.CurrentWeatherModel
import com.morley.tom.weatherquiz.openweather.models.WeatherModel
import io.reactivex.Flowable
import io.reactivex.plugins.RxJavaPlugins
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


interface OpenWeatherService {


    @GET("/data/2.5/weather")
    fun getWeather(@Query("q") city : String, @Query("APPID") key : String, @Query("units") units : String) : Call<CurrentWeatherModel>

    companion object{

        fun getWeatherApi() : Retrofit{
            val BASE_URL = "http://api.openweathermap.org"

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        const val apiKey = "f949bef88fd46eafa7a77d80587856ad"

    }

}