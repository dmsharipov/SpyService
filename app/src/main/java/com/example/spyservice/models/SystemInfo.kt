package com.example.spyservice.models

import android.content.pm.ApplicationInfo

data class SystemInfo(
    val osVersion: String,
    val sdkVersion: Int,
    val freeSpace: Long,
    val appList: List<String>,
    val procList: List<String>,
    val accList: List<String>
)
