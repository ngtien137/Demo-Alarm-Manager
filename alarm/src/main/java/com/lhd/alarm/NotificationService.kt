package com.lhd.alarm

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import kotlin.random.Random

class NotificationService : Service() {

    companion object{
        var OPEN_SCREEN : Class<*>?=null
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val title = intent?.getStringExtra(EXTRA_TITLE)?:"Alarm Notification"
        val message = intent?.getStringExtra(EXTRA_MESSAGE)?:"Alarm Notification Message"
        val requestCode = intent?.getIntExtra(EXTRA_NOTI_ID,0)?:0
        val notiBuilder = NotiBuilder()
        val showIntent = Intent(this,
            OPEN_SCREEN
        )
        val showPendingIntent = PendingIntent
            .getActivity(this,
                requestCode, showIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        notiBuilder.createNotification(this,Channel("AlarmChannelId","AlarmChannel","AlarmChannel"),
            Info(title =title ,message =message ),showPendingIntent)
        notiBuilder.showNotificationForeground(this,idNotify = requestCode)
        return START_NOT_STICKY
    }


}