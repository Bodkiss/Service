package com.example.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.service.databinding.FragmentTimerBinding
import kotlin.math.roundToInt


class TimerFragment : Fragment() {

    private lateinit var binding: FragmentTimerBinding

    private val timerUseCase = TimerUseCase()

    private var timerStarted = false
    private lateinit var  serviceIntent:Intent
    private var time = 0.0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       // return inflater.inflate(R.layout.fragment_timer, container,false)
        binding = FragmentTimerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnStartStop.setOnClickListener{
            startStopTimer()
        }
        binding.btnReset.setOnClickListener {
            timerUseCase.resetTimer()
            binding.timeTV.text = timerUseCase.getTimeStringFromDouble(time)
        }

        serviceIntent = Intent(requireContext(),TimerService::class.java)
        requireActivity().registerReceiver(timerUseCase.updateTime, IntentFilter(TimerService.TIMER_UPDATED))
        binding.timeTV.text = timerUseCase.getTimeStringFromDouble(time)
    }
    companion object{
        fun newInstance() = TimerFragment()
    }
    private fun startStopTimer(){
        if (timerStarted){
            timerUseCase.stopTimer()
            requireActivity().startService(serviceIntent)
            binding.btnStartStop.text = "Stop"

        }else{
            timerUseCase.startTimer()
            requireActivity().startService(serviceIntent)
            binding.btnStartStop.text = "Stop"

        }
    }
/*
    private val updateTime: BroadcastReceiver = object : BroadcastReceiver(){
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
        requireActivity().startService(serviceIntent)
        binding.btnStartStop.text = "Stop"
        timerStarted = true
    }

    private fun stopTimer() {
        requireActivity().stopService(serviceIntent)
        binding.btnStartStop.text = "Start"
        timerStarted = false
    }

    private fun resetTimer() {
        stopTimer()
        time = 0.0
        binding.timeTV.text = getTimeStringFromDouble(time)
    }*/

}