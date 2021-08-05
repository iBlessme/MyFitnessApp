package com.example.myfitnessapp

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import com.example.myfitnessapp.Service.StepsHelper
import com.example.myfitnessapp.Service.StepsService
import com.example.myfitnessapp.callBacks.stepsCallBack
import kotlinx.android.synthetic.main.fragment_steps.*

class StepsFragment : Fragment(), stepsCallBack{


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_steps, container, false)
    }

    override fun onStart() {
        super.onStart()

        val intent = Intent(context, StepsService::class.java)
        requireActivity().startService(intent)
        StepsService.subscribe.register(this)

    }
    override fun subscribeSteps(steps: Int) {
        TV_STEPS.setText(steps.toString())

        TV_CALORIES.setText(getCalories(steps))
        TV_DISTANCE.setText(getDistanceCovered(steps))
    }
    fun getCalories(steps: Int): String? {
        val Cal = (steps * 0.045).toInt()
        return "$Cal калориев"
    }

    fun getDistanceCovered(steps: Int): String? {
        val feet = (steps * 2.5).toInt()
        val distance = feet/3.281
        val finalDistance:Double = String.format("%.2f", distance).toDouble()
        return "$finalDistance метров"
    }

}