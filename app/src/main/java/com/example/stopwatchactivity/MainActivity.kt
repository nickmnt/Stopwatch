package com.example.stopwatchactivity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var running = false
    var seconds = 0
    var wasRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds")
            running = savedInstanceState.getBoolean("running")
            wasRunning = savedInstanceState.getBoolean("wasRunning")
        }
        createAndRunTimer()
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)

        savedInstanceState.putInt("seconds", seconds)
        savedInstanceState.putBoolean("running", running)
        savedInstanceState.putBoolean("wasRunning", wasRunning)
    }

    override fun onPause() {
        super.onPause()

        wasRunning = running
        running = false
    }

    override fun onResume() {
        super.onResume()

        if(wasRunning)
            running = true
    }

    fun startClicked(view: View) {
        running = true
    }

    fun stopClicked(view: View) {
        running = false
    }

    fun restartClicked(view: View) {
        running = false
        seconds = 0
    }

    private fun createAndRunTimer() {
        val handler = Handler(Looper.myLooper()!!)
        handler.post(object : Runnable {
            override fun run() {
                //Clearly this stopwatch does NOT account for drift
                val hours = seconds / 3600
                val minutes = (seconds % 3600) / 60
                val seconds1 = seconds % 60

                val time = String.format(
                    Locale.getDefault(),
                    "%d:%02d:%02d", hours, minutes, seconds1
                )
                textView.text = time
                if (running)
                    seconds++
                handler.postDelayed(this, 1000)
            }
        })
    }
}