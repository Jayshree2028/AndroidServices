package com.example.servicesexample

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlin.concurrent.thread

class LoggerService : Service() {

    val TAG = "Services"
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate called ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called ")
    }

    //Can call multiple time & execute on main thread(where UI is going on)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand called ")
        thread(start = true) {
            while (true) {
                Log.d(TAG, "Logging Message.... ")
                Thread.sleep(1000)
            }
        }
        return super.onStartCommand(intent, flags, startId)

    }

    //We used below method for Bound Services
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}