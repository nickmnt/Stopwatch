package com.example.stopwatchactivity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var running = false
    var seconds = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createTimerAndRun()
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

    private fun createTimerAndRun() {
        Handler(Looper.getMainLooper()).postDelayed({
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
        }, 3000)
    }
}