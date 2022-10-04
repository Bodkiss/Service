package com.example.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlin.math.roundToInt

class TimerUseCase {
    private var timerStarted = false
    private lateinit var  serviceIntent: Intent
    private var time = 0.0

     val updateTime: BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(TimerService.TIME_EXTRA,0.0)
           // binding.timeTV.text = getTimeStringFromDouble(time)
        }
    }

     fun getTimeStringFromDouble(time: Double): String {
        val resultInt = time.roundToInt()
        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val secounds = resultInt % 86400 % 3600 % 60

        return  makeTimeString(hours, minutes, secounds)
    }

    private fun makeTimeString(hours: Int, minutes: Int, secounds: Int): String = String.format(
        "%02d:%02d:%02d",hours,minutes,secounds)

    /* fun startStopTimer(){
        if (timerStarted){
            stopTimer()
        }else{
            startTimer()
        }
    }*/

    fun startTimer() {
        if (this::serviceIntent.isInitialized)
        serviceIntent.putExtra(TimerService.TIME_EXTRA,time)
      /*  requireActivity().startService(serviceIntent)
        binding.btnStartStop.text = "Stop"*/
        timerStarted = true
    }

     fun stopTimer() {
         /*requireActivity().stopService(serviceIntent)
        binding.btnStartStop.text = "Start"*/
        timerStarted = false
    }

     fun resetTimer() {
        stopTimer()
        time = 0.0
        //binding.timeTV.text = getTimeStringFromDouble(time)
    }

}