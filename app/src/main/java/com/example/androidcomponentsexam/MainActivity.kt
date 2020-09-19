package com.example.androidcomponentsexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_service.setOnClickListener {
            startActivity(Intent(this, ServiceActivity::class.java))
        }
        btn_broad_cast.setOnClickListener {
            startActivity(Intent(this, BroadCastReceiverActivity::class.java))
        }
        btn_content_provider.setOnClickListener {
            startActivity(Intent(this, ContentProviderActivity::class.java))
        }
    }
}