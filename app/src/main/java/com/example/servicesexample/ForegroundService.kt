package com.example.servicesexample

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import kotlin.concurrent.thread

class ForegroundService : Service() {

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
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand called ")
        thread(start = true) {
            while (true) {
                Log.d(TAG, "Logging Message.... ")
                Thread.sleep(1000)
            }
        }
        startForegroundLoggerService()
        return super.onStartCommand(intent, flags, startId)

    }

    //We used below method for Bound Services
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun startForegroundLoggerService(){
        createNotificationChannel()
        val notification= createNotification()
        startForeground(111,notification)
    }
    fun getPendingIntent(): PendingIntent {
        val intent = Intent(this, MainActivity::class.java)
        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(): NotificationChannel {
        val channel =
            NotificationChannel("ID", "ServiceExample", NotificationManager.IMPORTANCE_DEFAULT)
        val notificationManager =
            ContextCompat.getSystemService(this, NotificationManager::class.java)
        notificationManager!!.createNotificationChannel(channel)
        return channel

    }

    fun createNotification(): Notification {
        val notification = NotificationCompat.Builder(this, "ID")
            .setContentText("ForegroundService Running")
            .setContentTitle("Services")
            .setContentIntent(getPendingIntent())
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)
            .build()
        return notification
    }
}