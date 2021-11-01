package com.example.spyservice.api

import com.example.spyservice.models.SystemInfo
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

// Описываю методы, типы и эндпоинты
// При вызове sendSystemInfo отправится запрос на эндпоинт /systemInfo с хэдэром Content-Type и бади
interface Api {
    @POST("/systemInfo")
    @Headers("Content-Type: application/json")
    fun sendSystemInfo(@Body systemInfo: SystemInfo)
}