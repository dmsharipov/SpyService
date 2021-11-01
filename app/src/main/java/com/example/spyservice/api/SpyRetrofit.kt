package com.example.spyservice.api

import com.example.spyservice.models.SystemInfo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SpyRetrofit {
    companion object{
        private const val SERVER_IP = "192.168.1.195"
    }

    // "Скармливаем" ретрофиту класс с эндпоинтами для формирования запроса
    private val api = Retrofit.Builder()
        .baseUrl(SERVER_IP)
        // Подсказка ретрофиту, как из объекта сделать json
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)

    fun sendData(systemInfo: SystemInfo){
        api.sendSystemInfo(systemInfo)
    }
}