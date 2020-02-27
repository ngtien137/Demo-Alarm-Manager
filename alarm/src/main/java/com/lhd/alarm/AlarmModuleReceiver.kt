package com.lhd.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log

class AlarmModuleReceiver : BroadcastReceiver() {

    val TAG = AlarmModule.TAG

    override fun onReceive(context: Context?, intent: Intent?) {
        val title = intent?.getStringExtra(EXTRA_TITLE)?:""
        val message = intent?.getStringExtra(EXTRA_MESSAGE)?:""
        Log.e(TAG,"Broadcast Received: $title, Message: $message")
        val reminderServiceIntent = Intent(context,NotificationService::class.java)
        /**
         * Truyền data của intent ban đầu sang bao gồm title, message, notify id
         */
        reminderServiceIntent.putExtras(intent?:Intent())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context?.startForegroundService(reminderServiceIntent)
        } else {
            context?.startService(reminderServiceIntent)
        }
    }
}