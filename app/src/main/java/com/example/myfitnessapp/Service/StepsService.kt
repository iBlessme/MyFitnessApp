package com.example.myfitnessapp.Service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.example.myfitnessapp.StepsFragment
import com.example.myfitnessapp.callBacks.stepsCallBack
import kotlin.math.roundToInt

class StepsService : Service(), SensorEventListener{

    private var runnig = false
    private var totalSteps = 0f
    private var previousTotalSteps = 0f

    companion object {
        lateinit var callback: stepsCallBack
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        runnig = true
        val sensorManager: SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val countSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if(countSensor != null){
            Toast.makeText(this, "Счетчик шагов активирован", Toast.LENGTH_SHORT).show()
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }else{
            Toast.makeText(this, "Счетчик шагов не активен", Toast.LENGTH_SHORT).show()
        }

        return START_STICKY
    }


    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(runnig){
            totalSteps = event!!.values[0]
            val currentSteps: Int = totalSteps.toInt() - previousTotalSteps.toInt()
            callback?.subscribeSteps(currentSteps)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Log.d("SERVICE", sensor.toString())
    }

    object subscribe {
        fun register(activity: StepsFragment) {
            callback = activity as stepsCallBack
        }
    }


}