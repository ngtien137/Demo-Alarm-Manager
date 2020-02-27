package com.lhd.alarmmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.lhd.alarm.AlarmModule
import com.lhd.alarm.NotificationService

class MainActivity : AppCompatActivity() {

    val alarmId = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NotificationService.OPEN_SCREEN = this::class.java
    }

    fun onCreateAlarmClick(v:View?){
        AlarmModule.createAlarm(this,alarmId,-1L,this::class.java)
    }

    fun onCancelAlarmClick(view: View) {
        AlarmModule.cancel(this,alarmId)
    }
}
