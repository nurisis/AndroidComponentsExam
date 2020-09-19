package com.example.androidcomponentsexam

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class MyBindService : Service() {
    private var binder: IBinder? = null

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d("LOG>>", "[${System.identityHashCode(this)}] *** onStartCommand >> flags:$flags, startId:$startId")
        Toast.makeText(this, "Bind service starting", Toast.LENGTH_SHORT).show()

        // If we get killed, after returning from here, restart
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return binder
    }

    override fun onUnbind(intent: Intent): Boolean {
        // All clients have unbound with unbindService()
        return false
    }

    override fun onRebind(intent: Intent) {
        // A client is binding to the service with bindService(),
        // after onUnbind() has already been called
    }

    override fun onDestroy() {
        Log.e("LOG>>", "BInd onDestroy *****")
        Toast.makeText(this, "BInd service done", Toast.LENGTH_SHORT).show()
    }
}
