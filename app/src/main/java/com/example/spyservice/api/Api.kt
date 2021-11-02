package com.example.spyservice.api

import com.example.spyservice.models.SystemInfo
import retrofit2.Call
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

// Описываю методы, типы и эндпоинты
// При вызове sendSystemInfo отправится запрос на эндпоинт /systemInfo с хэдэром Content-Type и бади
interface Api {
    @POST("/")
    //@GET("/")
    @Headers("Content-Type: application/json", "Connection: close")
    fun sendSystemInfo(@Body systemInfo: SystemInfo) : Call<ResponseBody>
    //fun sendSystemInfo() : Call<ResponseBody>
}