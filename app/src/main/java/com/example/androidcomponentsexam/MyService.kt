package com.example.androidcomponentsexam

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.*
import android.os.Process.THREAD_PRIORITY_BACKGROUND
import android.util.Log
import android.widget.Toast

class MyService : Service() {

    private var serviceLooper: Looper? = null
    private var serviceHandler: ServiceHandler? = null

    // Handler that receives messages from the thread
    private inner class ServiceHandler(looper: Looper) : Handler(looper) {

        override fun handleMessage(msg: Message) {
            // Normally we would do some work here, like download a file.
            // For our sample, we just sleep for 5 seconds.
            try {
                Thread.sleep(5000)
            } catch (e: InterruptedException) {
                // Restore interrupt status.
                Thread.currentThread().interrupt()
            }

            for(i in 1..1000) {
                Thread.sleep(1000)
                println("LOG>> [${Thread.currentThread()}] $i")

                if(i == 2)
                    stopForeground(true)
            }

            // Stop the service using the startId, so that we don't stop
            // the service in the middle of handling another job
            stopSelf(msg.arg1)
        }
    }

    override fun onCreate() {
        // Start up the thread running the service.  Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block.  We also make it
        // background priority so CPU-intensive work will not disrupt our UI.
        HandlerThread("ServiceStartArguments", THREAD_PRIORITY_BACKGROUND).apply {
            start()

            // Get the HandlerThread's Looper and use it for our Handler
            serviceLooper = looper
            serviceHandler = ServiceHandler(looper)
        }

    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d("LOG>>", "[${System.identityHashCode(this)}] *** onStartCommand >> flags:$flags, startId:$startId")
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show()

        if(intent.hasExtra("flag"))
            startForegroundService()
        else {
            // For each start request, send a message to start a job and deliver the
            // start ID so we know which request we're stopping when we finish the job
            serviceHandler?.obtainMessage()?.also { msg ->
                msg.arg1 = startId
                serviceHandler?.sendMessage(msg)
            }
        }

        // If we get killed, after returning from here, restart
        return START_STICKY
    }

    /**
     * Execute foreground service
     * */
    private val CHANNEL_ID = "CHANNEL_HERE"
    private val ONGOING_NOTIFICATION_ID = 22
    private fun startForegroundService() {
        Log.d("LOG>>", "******* startForegroundService[${Thread.currentThread()}] *******")
        val pendingIntent: PendingIntent = Intent(this, ServiceActivity::class.java).let {
            PendingIntent.getActivity(this, 0, it, 0)
        }

        // 안드로이드8.0(26)부터 notification channel 이 필요함.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID,
                "푸시 알림",
                NotificationManager.IMPORTANCE_HIGH)

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        // TODO :: sdk 버전 별 분기해줘
        val notification = Notification.Builder(this, CHANNEL_ID)
            .setContentTitle("Noti title")
            .setContentText("Noti content")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setTicker("Noti ticker")
            .build()

        // Notification ID cannot be 0.
        startForeground(ONGOING_NOTIFICATION_ID, notification)

        serviceHandler?.obtainMessage()?.also { msg ->
            msg.arg1 = 33
            serviceHandler?.sendMessage(msg)
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        // We don't provide binding, so return null
        return null
    }

    override fun onDestroy() {
        Log.e("LOG>>", "onDestroy *****")
//        stopForeground(true)
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show()
    }
}
