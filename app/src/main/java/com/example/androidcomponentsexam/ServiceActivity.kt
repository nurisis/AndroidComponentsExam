package com.example.androidcomponentsexam

import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_service.*

class ServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        btn_start_foreground.setOnClickListener {
            val intent =   Intent(this, MyService::class.java).apply {
                putExtra("flag", "flag")
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent)
            }
            else {
                startService(intent)
            }
        }
        btn_stop_foreground.setOnClickListener {
            stopService(Intent(this, MyService::class.java))
        }


        btn_start.setOnClickListener {
            startService(
                Intent(this, MyService::class.java)
            )
        }
        btn_stop.setOnClickListener { stopService(Intent(this, MyService::class.java)) }

        // TODO :: bind service example
//        btn_start_bind.setOnClickListener {
//            bindService( Intent(this, MyService::class.java))
//        }
//        btn_stop_bind.setOnClickListener { stopService(Intent(this, MyService::class.java)) }

        btn_start_intent.setOnClickListener {
            MyIntentService.startActionFoo(this, "1", "2")
        }
        btn_stop_intent.setOnClickListener {
            stopService(Intent(this, MyIntentService::class.java))
        }

    }
}