package com.example.spyservice.api

import com.example.spyservice.models.SystemInfo
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import okhttp3.OkHttpClient

class SpyRetrofit {
    companion object{
        private const val SERVER_URL = "http://46.148.227.132:8082"
    }

    var logging = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    var client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    // "Скармливаем" ретрофиту класс с эндпоинтами для формирования запроса
    private val api = Retrofit.Builder()
        .baseUrl(SERVER_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        // Подсказка ретрофиту, как из объекта сделать json
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(Api::class.java)

    fun sendData(systemInfo: SystemInfo){
        api.sendSystemInfo(systemInfo).execute()
        //api.sendSystemInfo().execute()
    }
}