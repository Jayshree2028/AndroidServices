package com.example.servicesexample

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    lateinit var btnStartService: Button
    lateinit var btnStopService: Button
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

//        val intent = Intent(this, BackgroundService::class.java)
//        startService(intent)

        btnStartService = findViewById(R.id.btStartService)
        btnStopService = findViewById(R.id.btStopService)
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                200
            )

        }
        btnStartService.setOnClickListener {

//            val intent = Intent(this, LoggerService::class.java)
//            startService(intent)
            val intent = Intent(this, ForegroundService::class.java)
            startForegroundService(intent)

        }
        btnStopService.setOnClickListener{
            stopService(Intent(this,ForegroundService::class.java))
        }

    }
}