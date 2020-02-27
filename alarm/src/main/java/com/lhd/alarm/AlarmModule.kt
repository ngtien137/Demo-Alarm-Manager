package com.lhd.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import java.util.*

object AlarmModule {

    private lateinit var alarmManager:AlarmManager
    val TAG = "AM_TAG"

    fun createAlarm(context:Context,alarmCode:Int,timeAlarm:Long=-1L,showClass:Class<*>){
        val cal = Calendar.getInstance().apply {
            if (timeAlarm==-1L)
                timeInMillis+=10000L
            else
                timeInMillis = timeAlarm
        }
        val text = "Set alarm at: ${cal.timeInMillis.toStringDateFormat()}"
        Log.e(TAG,text)
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

        val intentReceiver = Intent(context,AlarmModuleReceiver::class.java)
        intentReceiver.putExtra(EXTRA_NOTI_ID,alarmCode)
        intentReceiver.putExtra(EXTRA_TITLE, "Alarm Title")
        intentReceiver.putExtra(EXTRA_MESSAGE, "Test Data Alarm")
        val operationIntent = PendingIntent.getBroadcast(context,alarmCode,intentReceiver,PendingIntent.FLAG_UPDATE_CURRENT)
        val showIntent = PendingIntent.getActivity(context,0,Intent(context,showClass),PendingIntent.FLAG_UPDATE_CURRENT)

        if (!::alarmManager.isInitialized)
            alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            alarmManager.setAlarmClock(AlarmManager.AlarmClockInfo(cal.timeInMillis,showIntent),operationIntent)
        }else{
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,cal.timeInMillis,operationIntent)
        }
    }

    fun cancel(context:Context,alarmCode: Int){
        val intent = Intent(context, AlarmModuleReceiver::class.java)
        alarmManager.cancel(PendingIntent.getBroadcast(context,alarmCode,intent,PendingIntent.FLAG_UPDATE_CURRENT))
    }
}