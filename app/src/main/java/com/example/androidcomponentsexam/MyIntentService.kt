package com.example.androidcomponentsexam

import android.app.*
import android.content.Intent
import android.content.Context
import android.os.Build
import android.os.Handler
import android.util.Log
import android.widget.Toast
import kotlin.coroutines.coroutineContext

// TODO: Rename actions, choose action names that describe tasks that this
// IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
private const val ACTION_FOO = "com.example.androidcomponentsexam.action.FOO"
private const val ACTION_BAZ = "com.example.androidcomponentsexam.action.BAZ"

// TODO: Rename parameters
private const val EXTRA_PARAM1 = "com.example.androidcomponentsexam.extra.PARAM1"
private const val EXTRA_PARAM2 = "com.example.androidcomponentsexam.extra.PARAM2"

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        startForegroundService()

        when (intent?.action) {
            ACTION_FOO -> {
                val param1 = intent.getStringExtra(EXTRA_PARAM1)
                val param2 = intent.getStringExtra(EXTRA_PARAM2)
                handleActionFoo(param1, param2)
            }
            ACTION_BAZ -> {
                val param1 = intent.getStringExtra(EXTRA_PARAM1)
                val param2 = intent.getStringExtra(EXTRA_PARAM2)
                handleActionBaz(param1, param2)
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionFoo(param1: String?, param2: String?) {
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private fun handleActionBaz(param1: String?, param2: String?) {
        Log.d("LOG>>", "[${Thread.currentThread()}]handleActionBaz !!!")
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
    }


    companion object {
        /**
         * Starts this service to perform action Foo with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        // TODO: Customize helper method
        @JvmStatic
        fun startActionFoo(context: Context, param1: String, param2: String) {
            val intent = Intent(context, MyIntentService::class.java).apply {
                action = ACTION_FOO
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }

        /**
         * Starts this service to perform action Baz with the given parameters. If
         * the service is already performing a task this action will be queued.
         *
         * @see IntentService
         */
        // TODO: Customize helper method
        @JvmStatic
        fun startActionBaz(context: Context, param1: String, param2: String) {
            Log.d("LOG>>", "startActionBaz !!!")
            val intent = Intent(context, MyIntentService::class.java).apply {
                action = ACTION_BAZ
                putExtra(EXTRA_PARAM1, param1)
                putExtra(EXTRA_PARAM2, param2)
            }
            context.startService(intent)
        }
    }

    override fun onDestroy() {
        Log.d("LOG>>", "intent service destroy !!!")
        super.onDestroy()
    }
}
