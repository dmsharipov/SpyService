package com.example.spyservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import com.example.spyservice.services.SpyWorker
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        private const val WORKER_TAG = "SpyWorker"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        // Оркестратор воркеров с задачами в виде отдельных потоков
        WorkManager.getInstance(this)
            .getWorkInfosByTagLiveData(WORKER_TAG)
            //"Подписываемся" на изменения наших воркеров
            .observe(this) { workInfo: List<WorkInfo> ->
                textView.text = when {
                    workInfo.any { it.state == WorkInfo.State.RUNNING } -> {
                        getString(R.string.service_is_running)
                    }
                    workInfo.all { it.state == WorkInfo.State.SUCCEEDED } -> {
                        getString(R.string.service_is_not_running)
                    }
                    else -> {
                        //getString(R.string.service_has_not_finished)
                        getString(R.string.service_is_not_running)
                    }
                }
            }
    }

    private fun initViews() {
        start_button.setOnClickListener {
            // Запуск работы только при подключении к интернету
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

            val spyWorkRequest = OneTimeWorkRequestBuilder<SpyWorker>()
                .addTag(WORKER_TAG)
                .setConstraints(constraints)
                .build()
            WorkManager.getInstance(this).enqueue(spyWorkRequest)
        }

        stop_button.setOnClickListener {
           WorkManager.getInstance(this).cancelAllWorkByTag(WORKER_TAG)
        }
    }
}
