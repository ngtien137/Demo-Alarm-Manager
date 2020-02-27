package com.lhd.alarm

import android.app.*
import android.content.Context
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.widget.RemoteViews

import androidx.core.app.NotificationCompat
import kotlin.random.Random

class NotiBuilder {
    val TAG = AlarmModule.TAG

    var NOTIFICATION_ID = 1
    private var channel: Channel? = null
    lateinit var notification: Notification
    var remoteViews: RemoteViews? = null
    private lateinit var context: Context
    private lateinit var notiManager: NotificationManager

    fun createNotification(
        context: Context,
        channel: Channel,
        notiInfo: Info,
        intent: PendingIntent
    ) {
        Log.e(
            TAG,
            "Create Notification Utils"
        )
        this.channel = channel
        this.context = context
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(context, channel.channelId)
                .setSmallIcon(notiInfo.smallIcon)
                .setContentTitle(notiInfo.title)
                .setContentText(notiInfo.message)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setOngoing(false)
                .setContentIntent(intent)
        /**
        Trên android 8 cần channel để hiển thị notification
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val ch = NotificationChannel(channel.channelId, channel.channelName, importance)
            ch.description = channel.channelDescription
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(ch)
        }
        /**
         * Remote view được sử dụng để custom view thông báo
         * Lưu ý: Remote view chỉ hỗ trợ 1 vài view cơ bản như imageview,textview,button,...
         */
        remoteViews = RemoteViews(context.packageName, R.layout.mynotification)
        remoteViews!!.setTextViewText(R.id.tvMessage, notiInfo.message)
        remoteViews!!.setTextViewText(R.id.tvTitle, notiInfo.title)
        builder.setCustomHeadsUpContentView(remoteViews)
        builder.setCustomBigContentView(remoteViews)
        builder.setCustomContentView(remoteViews)
        notification = builder.build()
    }

    fun showNotificationForeground(service: Service,removeNotify:Boolean = false,idNotify:Int=-1) {
        val id = if(idNotify==-1) NOTIFICATION_ID else idNotify
        Log.e(TAG, "Show notification Foreground with id: $id")
        service.startForeground(id, notification)
        service.stopForeground(removeNotify)
    }

}

