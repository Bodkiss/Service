package com.example.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.service.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        supportFragmentManager.beginTransaction()
            .replace(R.id.placeHolder, TimerFragment.newInstance())
            .commit()

    }
}
/*
        binding.btnStartStop.setOnClickListener{
            startStopTimer()
        }
        binding.btnReset.setOnClickListener {
            resetTimer()
        }

        serviceIntent = Intent(applicationContext,TimerService::class.java)
        registerReceiver(updateTime, IntentFilter(TimerService.TIMER_UPDATED))*/


/* private val updateTime:BroadcastReceiver = object : BroadcastReceiver(){
     override fun onReceive(context: Context, intent: Intent) {
         time = intent.getDoubleExtra(TimerService.TIME_EXTRA,0.0)
         binding.timeTV.text = getTimeStringFromDouble(time)
     }
 }

 private fun getTimeStringFromDouble(time: Double): String {
     val resultInt = time.roundToInt()
     val hours = resultInt % 86400 / 3600
     val minutes = resultInt % 86400 % 3600 / 60
     val secounds = resultInt % 86400 % 3600 % 60

     return  makeTimeString(hours, minutes, secounds)
 }

 private fun makeTimeString(hours: Int, minutes: Int, secounds: Int): String = String.format(
     "%02d:%02d:%02d",hours,minutes,secounds)

 private fun startStopTimer(){
     if (timerStarted){
         stopTimer()
     }else{
         startTimer()
     }
 }

 private fun startTimer() {
     serviceIntent.putExtra(TimerService.TIME_EXTRA,time)
     startService(serviceIntent)
     binding.btnStartStop.text = "Stop"
     timerStarted = true
 }

 private fun stopTimer() {
     stopService(serviceIntent)
     binding.btnStartStop.text = "Start"
     timerStarted = false
 }

 private fun resetTimer() {
     stopTimer()
     time = 0.0
     binding.timeTV.text = getTimeStringFromDouble(time)
 }
*/

