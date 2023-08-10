package com.sample.spinningwheel

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
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
        val random = Random()


        binding.btnTarget.setOnClickListener(View.OnClickListener {
            // disabling the button so that user
            // should not click on the button
            // while the wheel is spinning
            binding.btnTarget.isEnabled = false

            // reading random value between 10 to 30
            var spin: Int = 8
//                random.nextInt(10) + 10

            Log.d("TAG spin", "onCreate: $spin ")
            // since the wheel has 10 divisions, the
            // rotation should be a multiple of
            // 360/10 = 36 degrees
            spin *= 36

            // timer for each degree movement
            timer = object : CountDownTimer((spin * 10).toLong(), 1) {
                override fun onTick(l: Long) {
                    // rotate the wheel
                    val rotation = binding.icBoard.rotation + 2
                    binding.icBoard.rotation = rotation


                    Log.d("TAG spin", "onCreate: $rotation ")
                }




                override fun onFinish() {
                    // enabling the button again
                    binding.btnTarget.isEnabled = true
                }
            }.start()
        })

    }
}


/*
class MainActivity : AppCompatActivity() {
    var btnSpin: Button? = null
    var ivWheel: ImageView? = null
    var timer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.)

        // initializing views
        btnSpin = findViewById<Button>(R.id.btn)
        ivWheel = findViewById<ImageView>(R.id.ivWheel)

        // creating an object of Random class
        // to generate random numbers for the spin
        val random = Random()

        // on click listener for btnSpin
        btnSpin.setOnClickListener(View.OnClickListener {
            // disabling the button so that user
            // should not click on the button
            // while the wheel is spinning
            btnSpin.setEnabled(false)

            // reading random value between 10 to 30
            var spin = random.nextInt(20) + 10

            // since the wheel has 10 divisions, the
            // rotation should be a multiple of
            // 360/10 = 36 degrees
            spin = spin * 36

            // timer for each degree movement
            timer = object : CountDownTimer((spin * 20).toLong(), 1) {
                override fun onTick(l: Long) {
                    // rotate the wheel
                    val rotation = ivWheel.getRotation() + 2
                    ivWheel.setRotation(rotation)
                }

                override fun onFinish() {
                    // enabling the button again
                    btnSpin.setEnabled(true)
                }
            }.start()
        })
    }
}*/
