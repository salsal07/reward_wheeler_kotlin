package com.sample.spinningwheel

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.sample.spinningwheel.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var timer: CountDownTimer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val random = listOf<String>(
            "50_coin",
            "20_coin",
            "10_coin",
            "better_luck",
            "spin_again",
            "10_coin_second",
            "better_luck_second",
            "5_coin"
        )
        binding.btnTarget.setOnClickListener {
            //here refreshing rotation and button status
            binding.clBoard.rotation = 0f
            binding.btnTarget.isEnabled = false


            val findAngle: Int = findAngleReward(random.random())


            // Calculate the angle needed to reach the static value (certain degrees)
            //4 round static 1440 plus backend data
            var staticValueAngle = 1440 + findAngle


            timer = object : CountDownTimer((10000).toLong(), 1) {
                override fun onTick(l: Long) {

                    //rotation speed add here
                    binding.clBoard.rotation = speedControl(binding.clBoard.rotation, findAngle)

                    // Check if the current rotation angle reaches the  value angle
                    if (binding.clBoard.rotation >= staticValueAngle) {
                        timer?.cancel()
                        binding.btnTarget.isEnabled = true // Enable the button
                    }
                }

                override fun onFinish() {
                    binding.btnTarget.isEnabled = true
                }
            }.start()
        }


    }

    private fun findAngleReward(findAngle: String): Int {

        return when (findAngle) {
            "50_coin" -> 360
            "20_coin" -> 315
            "10_coin" -> 135
            "better_luck" -> 90
            "spin_again" -> 180
            "10_coin_second" -> 270
            "better_luck_second" -> 225
            "5_coin" -> 45

            else -> 90
        }
    }


    private fun speedControl(rotation: Float, findAngle: Int): Float {
        return if (rotation > 1440 + findAngle / 2) {
            binding.clBoard.rotation + 2
        } else if (rotation > 1440) {
            binding.clBoard.rotation + 3
        } else if (rotation > 1080) {
            binding.clBoard.rotation + 6
        } else if (rotation > 720) {
            binding.clBoard.rotation + 9
        } else {
            binding.clBoard.rotation + 15
        }
    }

}
