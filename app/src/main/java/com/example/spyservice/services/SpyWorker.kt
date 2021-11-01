package com.example.spyservice.services

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.spyservice.api.SpyRetrofit
import com.example.spyservice.util.SpyUtils

class SpyWorker(appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams) {
    private val spyRetrofit = SpyRetrofit()

    override fun doWork(): Result {
        sendInformation()
        return Result.success()
    }

    private fun sendInformation() {
        val systemInfo = SpyUtils.getSystemInfo(applicationContext)
        Log.d("SYSINFO", systemInfo.toString())
        //spyRetrofit.sendData(systemInfo)
    }
}
